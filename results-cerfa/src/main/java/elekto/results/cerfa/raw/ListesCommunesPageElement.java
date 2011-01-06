package elekto.results.cerfa.raw;

import static elekto.results.cerfa.raw.BasePageElement.FONT_HELVETICA;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

/**
 * Elements de page PDF pour renseigner les listes communes.
 */
enum ListesCommunesPageElement
        implements PageElement {

    C00_LISTE_NOM(193f, 108.5f, 0f, -11.35f, 122f, true),
    C01_ORGANISATIONS(318f, 108.5f, 0f, -11.35f, 160f, true),
    C02_SUFFRAGES_REPARTITION(481f, 108.5f, 0f, -11.35f, 103f, true);

    ListesCommunesPageElement(
            final float textPositionX,
            final float textPositionY,
            final float horizontalStep,
            final float verticalStep,
            final float maxTextWidth,
            final boolean center)
    {
        this.pageElement = new BasePageElement(
                0,
                FONT_HELVETICA,
                7f,
                Color.BLACK,
                textPositionX,
                textPositionY,
                horizontalStep,
                verticalStep,
                maxTextWidth,
                null,
                center);
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
