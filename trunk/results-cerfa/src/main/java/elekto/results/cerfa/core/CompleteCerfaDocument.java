package elekto.results.cerfa.core;

import static com.google.common.collect.Lists.newArrayList;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import elekto.results.cerfa.model.ElectionModel;
import elekto.results.cerfa.raw.CerfaResource;
import elekto.results.cerfa.raw.RawCerfaDocument;

/**
 * Provides elections results as CERFA document with many page if required.
 * <p>
 * This implementation is based on {@link RawCerfaDocument}.
 */
public class CompleteCerfaDocument
        implements CerfaDocument {

    public CompleteCerfaDocument(
            final CerfaResource cerfaResource,
            final ElectionModel electionResults)
    {
        this.cerfaResource = cerfaResource;
        this.electionModel = electionResults;
    }


    @Override
    public void save(
            final OutputStream outputStream)
        throws IOException
    {
        final Collection<RawCerfaDocument> documents = newArrayList();

        //        for (final Liste liste : this.electionResults.getListes()) {
        //            for (final Candidat candidat : liste.getCandidats()) {
        //            }
        //        }

        for (int i = 0; i < 2; i++) {
            final RawCerfaDocument document = this.makeRawCerfa(this.electionModel);
            documents.add(document);
        }

        final int nombreTotalPages = documents.size() * RawCerfaDocument.PAGE_COUNT;
        int numeroPremierePage = 1;

        final PDDocument pdDocument = new PDDocument();
        for (final RawCerfaDocument document : documents) {
            // TODO document.setPageNumeros(numeroPremierePage, nombreTotalPages);

            document.drawTexts();

            for (final PDPage pdPage : document.getPages()) {
                pdDocument.addPage(pdPage);
            }

            numeroPremierePage += RawCerfaDocument.PAGE_COUNT;
        }

        try {
            pdDocument.save(outputStream);
        }
        catch (final COSVisitorException e) {
            throw new IOException(e);
        }
        pdDocument.close();

        for (final RawCerfaDocument document : documents) {
            document.close();
        }
    }


    private RawCerfaDocument makeRawCerfa(
            final ElectionModel electionModel)
        throws IOException
    {
        final RawCerfaDocument document = RawCerfaDocument.makeCerfa(this.cerfaResource);

        return document;
    }

    private final CerfaResource cerfaResource;

    private final ElectionModel electionModel;

}
