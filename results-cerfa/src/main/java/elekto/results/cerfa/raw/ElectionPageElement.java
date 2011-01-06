package elekto.results.cerfa.raw;

import static elekto.results.cerfa.raw.BasePageElement.FONT_HELVETICA_BOLD;
import static elekto.results.cerfa.raw.TextSpacingBuilders.DATE_SPACING;
import static org.apache.pdfbox.pdmodel.font.PDType1Font.ZAPF_DINGBATS;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;

/**
 * Elements de page PDF pour renseigner les informations de l'élection.
 */
enum ElectionPageElement
        implements PageElement {

    ELECTION_MANDAT_DUREE(FONT_HELVETICA_BOLD, 7f, 185f, 632f, 7f, null),
    ELECTION_COLLEGES_NOMBRE(FONT_HELVETICA_BOLD, 7f, 260f, 620f, 7f, null),
    ELECTION_PARTIELLE(ZAPF_DINGBATS, 10f, 259.5f, 607f, 10f, null),
    ELECTION_SCRUTIN_PRECEDENT_DATE(FONT_HELVETICA_BOLD, 7f, 238f, 593f, 35f, DATE_SPACING), ;

    ElectionPageElement(
            final PDFont pdFont,
            final float fontSize,
            final float textPositionX,
            final float textPositionY,
            final float maxTextWidth,
            final TextSpacingBuilder textSpacingBuilder)
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
                textSpacingBuilder,
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
