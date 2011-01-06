package elekto.results.cerfa.raw;

import java.awt.Color;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import elekto.results.cerfa.core.CerfaDocument;
import elekto.results.cerfa.model.ElectionModel;

/**
 * Provides a way to customize original CERFA document (ie: a raw PDF document) and to save modifications.
 * <p>
 * That implementation uses PdfBox to modify the CERFA PDF document.
 * <p>
 * {@link #close()} must be call at the end of the process.
 */
public class RawCerfaDocument
        extends ElectionModel
        implements Closeable, CerfaDocument {

    RawCerfaDocument(
            final CerfaResource cerfaResource)
        throws IOException
    {
        super();

        this.pdDocument = PDDocument.load(cerfaResource.getPdfResourceAsStream());
    }


    /**
     * {@inheritDoc}
     * 
     * @see java.io.Closeable#close()
     */
    @Override
    public void close()
        throws IOException
    {
        this.pdDocument.close();
    }


    public void drawTexts()
        throws IOException
    {
        this.draw(new EtablissementDrawableElement(this.getEtablissement()));
        this.draw(new CollegeDrawableElement(this.getCollege()));
        this.draw(new ElectionDrawableElement(this.getElection()));
        this.draw(new ListesCommunesDrawableElement(this.getListesCommunes()));

        this.draw(new PremierTourResultatsDrawableElement(this.getPremierTourResultats()));
        this.draw(new PremierTourCandidatsDrawableElement(this.getPremierTourCandidats()));

        this.draw(new SecondTourResultatsDrawableElement(this.getSecondTourResultats()));
        this.draw(new SecondTourCandidatsDrawableElement(this.getSecondTourCandidats()));

        this.draw(new MembresBureauVoteSignaturesDrawableElement(this.getMembresBureauVoteSignatures()));
        this.draw(new ContactEntrepriseDrawableElement(this.getContactEntreprise()));

        this.draw(new PageNumeroDrawableElement(this.getPageNumeros()));
    }


    public PDDocument getDocument()
    {
        return this.pdDocument;
    }


    public Iterable<PDPage> getPages()
    {
        final List<PDPage> pages = this.pdDocument.getDocumentCatalog().getAllPages();

        return pages;
    }


    /**
     * Save the PDF changes to the {@code OutputStream} which is closed after.
     * 
     * @param outputStream
     * @throws IOException
     */
    @Override
    public void save(
            final OutputStream outputStream)
        throws IOException
    {
        if (this.saved) {
            throw new UnsupportedOperationException("document cannot be saved twice, use a new instance.");
        }

        try {
            this.drawTexts();

            this.pdDocument.save(outputStream);
            outputStream.close();
            this.close();

            this.saved = true;
        }
        catch (final COSVisitorException e) {
            throw new IOException("unable to save Cerfa document", e);
        }
    }


    private void draw(
            final DrawableElement drawableElement)
        throws IOException
    {
        drawableElement.prepareText();
        drawableElement.draw(this.pdDocument);
    }


    private void drawGrid(
            final int pageIndex)
        throws IOException
    {
        final PDFont font = PDType1Font.HELVETICA_BOLD;
        final float fontSize = 4.0f;

        final PDPage pdPage = (PDPage) this.pdDocument.getDocumentCatalog().getAllPages().get(pageIndex);
        final PDPageContentStream contentStream = new PDPageContentStream(this.pdDocument, pdPage, true, true);

        for (float x = 0; x < PDPage.PAGE_SIZE_A4.getWidth(); x += 50) {
            for (float y = 0; y < PDPage.PAGE_SIZE_A4.getHeight(); y += 50) {
                contentStream.beginText();
                contentStream.setFont(font, fontSize);
                contentStream.setNonStrokingColor(Color.RED);

                contentStream.moveTextPositionByAmount(x, y);
                contentStream.drawString("[" + x + ", " + y + "]");

                contentStream.endText();
            }
        }

        contentStream.close();
    }


    public static RawCerfaDocument makeCerfa(
            final CerfaResource cerfaResource)
        throws IOException
    {
        final RawCerfaDocument cerfaDocument = new RawCerfaDocument(cerfaResource);

        return cerfaDocument;
    }

    public static final int PAGE_COUNT = 2;

    // TODO remove public static final int CANDIDATS_PREMIER_TOUR_ROW_COUNT = PremierTourCandidatsInfos.ROW_COUNT;

    // TODO remove public static final int CANDIDATS_SECOND_TOUR_ROW_COUNT = SecondTourCandidatsInfos.ROW_COUNT;

    private final PDDocument pdDocument;

    private boolean saved;

}
