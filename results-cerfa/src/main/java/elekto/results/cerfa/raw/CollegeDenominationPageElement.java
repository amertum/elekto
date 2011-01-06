package elekto.results.cerfa.raw;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * Elements de page PDF pour renseigner la dénomination du collège.
 */
enum CollegeDenominationPageElement
        implements PageElement {

    COLLEGE_DENOMINATION_UNIQUE(433f, 703f),
    COLLEGE_DENOMINATION_PREMIER(433f, 679f),
    COLLEGE_DENOMINATION_DEUXIEME(433f, 648.5f),
    COLLEGE_DENOMINATION_TROISIEME(433f, 625f),
    COLLEGE_DENOMINATION_AUTRE(433f, 601.5f), ;

    CollegeDenominationPageElement(
            final float textPositionX,
            final float textPositionY)
    {
        this.pageElement = new BasePageElement(
                0,
                PDType1Font.ZAPF_DINGBATS,
                10f,
                Color.BLACK,
                textPositionX,
                textPositionY,
                0f,
                0f,
                10f,
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
