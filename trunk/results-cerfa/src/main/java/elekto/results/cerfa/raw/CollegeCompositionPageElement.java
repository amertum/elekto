package elekto.results.cerfa.raw;

import static elekto.results.cerfa.raw.BasePageElement.FONT_HELVETICA;
import static org.apache.pdfbox.pdmodel.font.PDType1Font.ZAPF_DINGBATS;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;

/**
 * Elements de page PDF pour renseigner la composition du collège.
 */
enum CollegeCompositionPageElement
        implements PageElement {

    COLLEGE_COMPOSITION_OUVRIERS(ZAPF_DINGBATS, 10f, 553f, 699f, 10f),
    COLLEGE_COMPOSITION_EMPLOYES(ZAPF_DINGBATS, 10f, 553f, 685f, 10f),
    COLLEGE_COMPOSITION_TECHNICIENS(ZAPF_DINGBATS, 10f, 553f, 671f, 10f),
    COLLEGE_COMPOSITION_AGENTS_DE_MAITRISE(ZAPF_DINGBATS, 10f, 553f, 657f, 10f),
    COLLEGE_COMPOSITION_INGENIEURs(ZAPF_DINGBATS, 10f, 553f, 643f, 10f),
    COLLEGE_COMPOSITION_CADRES(ZAPF_DINGBATS, 10f, 553f, 629f, 10f),

    COLLEGE_COMPOSITION_AUTRES(ZAPF_DINGBATS, 10f, 553f, 615f, 10f),
    COLLEGE_COMPOSITION_AUTRES_VALUE1(FONT_HELVETICA, 6f, 475f, 615.5f, 66f),
    COLLEGE_COMPOSITION_AUTRES_VALUE2(FONT_HELVETICA, 6f, 475f, 608f, 66f),
    COLLEGE_COMPOSITION_AUTRES_VALUE3(FONT_HELVETICA, 6f, 475f, 600f, 66f), ;

    CollegeCompositionPageElement(
            final PDFont pdFont,
            final float fontSize,
            final float textPositionX,
            final float textPositionY,
            final float maxTextWidth)
    {
        this.pageElement = new BasePageElement(
                0,
                pdFont,
                fontSize,
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
