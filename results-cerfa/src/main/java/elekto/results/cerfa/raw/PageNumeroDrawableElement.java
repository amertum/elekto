package elekto.results.cerfa.raw;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

import elekto.results.cerfa.model.PageNumeroInfos;

/**
 * Informations sur les numéros de page.
 */
class PageNumeroDrawableElement
        implements DrawableElement {

    PageNumeroDrawableElement(
            final PageNumeroInfos pageNumeroInfos)
    {
        this.pageNumeroInfos = pageNumeroInfos;
    }


    @Override
    public void draw(
            final PDDocument pdDocument)
        throws IOException
    {
        PageNumeroPageElement.PAGE1_NUMERO.drawText(pdDocument, this.numeroPage1);
        PageNumeroPageElement.PAGE2_NUMERO.drawText(pdDocument, this.numeroPage2);
    }


    @Override
    public void prepareText()
    {
        final boolean bothPositive = (this.pageNumeroInfos.getNumeroPremierePage() > 0)
                && (this.pageNumeroInfos.getNombreTotalPages() > 0);
        if (bothPositive) {
            this.numeroPage1 = this.pageNumeroInfos.getNumeroPremierePage() + "/"
                    + this.pageNumeroInfos.getNombreTotalPages();
            this.numeroPage2 = (this.pageNumeroInfos.getNumeroPremierePage() + 1) + "/"
                    + this.pageNumeroInfos.getNombreTotalPages();
        }
        else {
            this.numeroPage1 = "";
            this.numeroPage2 = "";
        }

    }

    private final PageNumeroInfos pageNumeroInfos;

    private String numeroPage1;

    private String numeroPage2;

}
