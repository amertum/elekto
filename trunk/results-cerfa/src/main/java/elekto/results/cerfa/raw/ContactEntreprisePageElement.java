package elekto.results.cerfa.raw;

import static elekto.results.cerfa.raw.BasePageElement.FONT_HELVETICA;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

/**
 * Elements de page PDF pour renseigner les informations sur la personne à contacter dans l'entreprise.
 */
enum ContactEntreprisePageElement
        implements PageElement {

    NOM(58f, 149f, 257f),
    PRENOM(68f, 133.5f, 182f),
    FONCTION(123f, 119f, 127f),
    TELEPHONE(95f, 95.5f, 155f),
    TELECOPIE(95f, 80f, 155f),
    EMAIL(90f, 65f, 160f), ;

    ContactEntreprisePageElement(
            final float textPositionX,
            final float textPositionY,
            final float maxTextWidth)
    {
        this.pageElement = new BasePageElement(
                1,
                FONT_HELVETICA,
                7f,
                Color.BLACK,
                textPositionX,
                textPositionY,
                0f,
                0f,
                maxTextWidth,
                null,
                false);
    }


    @Override
    public void drawText(
            final PDDocument pdDocument,
            final String text)
        throws IOException
    {
        this.pageElement.drawText(pdDocument, text);
    }


    @Override
    public void drawText(
            final PDDocument pdDocument,
            final String text,
            final int horizontalIndex,
            final int verticalIndex)
        throws IOException
    {
        this.pageElement.drawText(pdDocument, text, horizontalIndex, verticalIndex);
    }

    private final PageElement pageElement;

}
