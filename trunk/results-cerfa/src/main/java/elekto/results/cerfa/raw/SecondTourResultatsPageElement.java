package elekto.results.cerfa.raw;

import static elekto.results.cerfa.raw.BasePageElement.FONT_HELVETICA_BOLD;
import static elekto.results.cerfa.raw.TextSpacingBuilder.textSpacing;
import static elekto.results.cerfa.raw.TextSpacingBuilders.DATE_SPACING;
import static org.apache.pdfbox.pdmodel.font.PDType1Font.ZAPF_DINGBATS;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;

/**
 * Elements de page PDF pour renseigner les résultats de l'élection au second tour.
 */
enum SecondTourResultatsPageElement
        implements PageElement {

    RESULTATS_SECOND_TOUR_DATE(FONT_HELVETICA_BOLD, 7f, 238f, 554f, 35f, DATE_SPACING, false),
    RESULTATS_SECOND_TOUR_ELECTEURS_INSCRITS_NOMBRE(FONT_HELVETICA_BOLD, 7f, 288f, 542f, 42f, null, false),
    RESULTATS_SECOND_TOUR_VOTANTS_NOMBRE(FONT_HELVETICA_BOLD, 7f, 288f, 529.5f, 42f, null, false),
    RESULTATS_SECOND_TOUR_BULLETINS_BLANCS_OU_NULS(FONT_HELVETICA_BOLD, 7f, 288f, 517.5f, 42f, null, false),
    RESULTATS_SECOND_TOUR_SUFFRAGES_VALABLES(FONT_HELVETICA_BOLD, 7f, 288f, 505.5f, 42f, null, false),

    RESULTATS_SECOND_TOUR_CARENCE_OUI(ZAPF_DINGBATS, 10f, 402.5f, 562f, 10f, null, false),
    RESULTATS_SECOND_TOUR_CARENCE_NON(ZAPF_DINGBATS, 10f, 426.5f, 562f, 10f, null, false),

    RESULTATS_SECOND_TOUR_LISTES_PRESENTEES_NOMBRES(FONT_HELVETICA_BOLD, 7f, 542.5f, 555.5f, 20f, textSpacing(2, 8f), false),
    RESULTATS_SECOND_TOUR_SIEGES_POURVOIR_NOMBRE(FONT_HELVETICA_BOLD, 7f, 542.5f, 538f, 20f, textSpacing(2, 8f), false),
    RESULTATS_SECOND_TOUR_QUOTIENT_ELECTORAL_NUMERATEUR(FONT_HELVETICA_BOLD, 6f, 498f, 515f, 25f, null, true),
    RESULTATS_SECOND_TOUR_QUOTIENT_ELECTORAL_DENOMINATEUR(FONT_HELVETICA_BOLD, 6f, 498f, 506f, 25f, null, true),
    RESULTATS_SECOND_TOUR_QUOTIENT_ELECTORAL(FONT_HELVETICA_BOLD, 6f, 525f, 510.5f, 30f, null, false), ;

    SecondTourResultatsPageElement(
            final PDFont pdFont,
            final float fontSize,
            final float textPositionX,
            final float textPositionY,
            final float maxTextWidth,
            final TextSpacingBuilder textSpacingBuilder,
            final boolean center)
    {
        this.pageElement = new BasePageElement(
                1,
                pdFont,
                fontSize,
                Color.BLACK,
                textPositionX - 72f,
                textPositionY + 236.5f,
                0f,
                0f,
                maxTextWidth,
                textSpacingBuilder,
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
