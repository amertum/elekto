package elekto.results.cerfa.raw;

import static elekto.results.cerfa.raw.BasePageElement.FONT_HELVETICA_BOLD;
import static elekto.results.cerfa.raw.TextSpacingBuilder.textSpacing;
import static elekto.results.cerfa.raw.TextSpacingBuilders.SIRET_TEXT_SPACING;
import static java.awt.Color.BLACK;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

/**
 * Elements de page PDF pour renseigner les informations de l'établissement.
 */
enum EtablissementPageElement
        implements PageElement {

    ETABLISSEMENT_RAISON_SOCIALE(0, 150f, 731f, 0f, 0f, 167f, null),
    ETABLISSEMENT_ADRESSE1(0, 135f, 719f, 0f, 0f, 182f, null),
    ETABLISSEMENT_ADRESSE2(0, 135f, 708f, 0f, 0f, 182f, null),
    ETABLISSEMENT_CODE_POSTAL(0, 144f, 694.5f, 0f, 0f, 22f, textSpacing(5, 10.5f)),
    ETABLISSEMENT_VILLE(0, 216f, 695f, 0f, 0f, 100f, null),
    ETABLISSEMENT_SIRET(0, 189.5f, 682f, 0f, 0f, 180f, SIRET_TEXT_SPACING),
    ETABLISSEMENT_AUTRES_SIRET(1, 363f, 325f, 0f, -10.5f, 165f, SIRET_TEXT_SPACING),
    ETABLISSEMENT_IDCC(0, 219.5f, 658.5f, 0f, 0f, 18f, textSpacing(4, 8f)), ;

    EtablissementPageElement(
            final int pageIndex,
            final float textPositionX,
            final float textPositionY,
            final float horizontalStep,
            final float verticalStep,
            final float maxTextWidth,
            final TextSpacingBuilder textSpacingBuilder)
    {
        this.pageElement = new BasePageElement(
                pageIndex,
                FONT_HELVETICA_BOLD,
                7f,
                BLACK,
                textPositionX,
                textPositionY,
                horizontalStep,
                verticalStep,
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
