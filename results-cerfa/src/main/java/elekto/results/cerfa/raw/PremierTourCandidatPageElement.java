package elekto.results.cerfa.raw;

import static elekto.results.cerfa.raw.BasePageElement.FONT_HELVETICA;
import static elekto.results.cerfa.raw.BasePageElement.FONT_HELVETICA_BOLD;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;

/**
 * Elements de page PDF pour renseigner les informations de chaque candidats au premier tour dont leurs résultats.
 */
enum PremierTourCandidatPageElement
        implements PageElement {

    // TODO for nom prenom, add feature to dynamically change the font to fit maxTextWidth, the fontSize should have a min-max bound
    C00_CANDIDAT_NOM_PRENOM(FONT_HELVETICA, 6f, 24.5f, 414f, 0f, -13.45f, 65f, true),
    C01_CANDIDAT_SEXE(FONT_HELVETICA, 7f, 104f, 414f, 0f, -13.45f, 10f, true),
    C02_CANDIDAT_SYNDICAT(FONT_HELVETICA, 7f, 117f, 414f, 0f, -13.45f, 73f, true),
    C03_LISTE_BULLETINS_VALABLES_NOMBRE(FONT_HELVETICA, 7f, 194f, 414f, 0f, -13.45f, 33f, true),
    C04_CANDIDAT_VOIX_NOMBRE(FONT_HELVETICA, 7f, 230f, 414f, 0f, -13.45f, 47f, true),
    C05_LISTE_CANDIDATS_VOIX_TOTAL(FONT_HELVETICA, 7f, 280f, 414f, 0f, -13.45f, 35f, true),
    C06_LISTE_CANDIDATS_NOMBRE(FONT_HELVETICA, 7f, 317f, 414f, 0f, -13.45f, 26f, true),
    C07_LISTE_VOIX_MOYENNE(FONT_HELVETICA, 7f, 345f, 414f, 0f, -13.45f, 30f, true),
    C08_LISTE_SIEGES_ATTRIBUES_QUOTIENT(FONT_HELVETICA, 7f, 377f, 414f, 0f, -13.45f, 35f, true),
    C09_LISTE_SIEGES_ATTRIBUES_PFM_SIEGE1(FONT_HELVETICA, 7f, 415f, 414f, 0f, -13.45f, 30f, true),
    C10_LISTE_SIEGES_ATTRIBUES_PFM_SIEGE2(FONT_HELVETICA, 7f, 447f, 414f, 0f, -13.45f, 30f, true),
    C11_LISTE_SIEGES_ATTRIBUES_PFM_SIEGE3(FONT_HELVETICA, 7f, 479f, 414f, 0f, -13.45f, 30f, true),
    C12_CANDIDAT_ELU(FONT_HELVETICA_BOLD, 7f, 512f, 414f, 0f, -13.45f, 35f, true),
    C13_LISTE_ELUS_NOMBRE(FONT_HELVETICA, 7f, 549f, 414f, 0f, -13.45f, 17f, true), ;

    PremierTourCandidatPageElement(
            final PDFont pdFont,
            final float fontSize,
            final float textPositionX,
            final float textPositionY,
            final float horizontalStep,
            final float verticalStep,
            final float maxTextWidth,
            final boolean center)
    {
        this.pageElement = new BasePageElement(
                0,
                pdFont,
                fontSize,
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
