package elekto.results.cerfa.raw;

import static elekto.results.cerfa.raw.BasePageElement.FONT_HELVETICA;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

/**
 * Elements de page PDF pour renseigner les numéros de page.
 */
enum PageNumeroPageElement
        implements PageElement {

    PAGE1_NUMERO(0, 550f, 20f, 26f),
    PAGE2_NUMERO(1, 550f, 20f, 26f);

    PageNumeroPageElement(
            final int pageIndex,
            final float textPositionX,
            final float textPositionY,
            final float maxTextWidth)
    {
        this.pageElement = new BasePageElement(
                pageIndex,
                FONT_HELVETICA,
                7f,
                Color.BLACK,
                textPositionX,
                textPositionY,
                0f,
                0f,
                maxTextWidth,
                null,
                true);
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
