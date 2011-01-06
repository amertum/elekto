package elekto.results.cerfa.raw;

import static elekto.results.cerfa.raw.BasePageElement.FONT_HELVETICA;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

/**
 * Elements de page PDF pour renseigner les informations sur les membres du bureau de vote en vue de leur signature.
 */
enum MembresBureauVoteSignaturesPageElement
        implements PageElement {

    C00_NOM_PRENOM(30f, 283.5f, 0f, -11.35f, 114f, true),
    C01_ORGANISATIONS(146f, 283.5f, 0f, -11.35f, 91f, true);

    MembresBureauVoteSignaturesPageElement(
            final float textPositionX,
            final float textPositionY,
            final float horizontalStep,
            final float verticalStep,
            final float maxTextWidth,
            final boolean center)
    {
        this.pageElement = new BasePageElement(
                1,
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
