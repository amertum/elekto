package elekto.results.cerfa.raw;

import static elekto.results.cerfa.raw.TextSpacingBuilder.textSpacing;

/**
 * Plusieurs {@link TextSpacingBuilder} prédéfinis.
 */
class TextSpacingBuilders {

    public static final TextSpacingBuilder SIRET_TEXT_SPACING = textSpacing(3, 8f).space(5f).chars(3, 8f).space(5f).chars(
            3,
            8f).space(5f).chars(5, 8f);

    public static final TextSpacingBuilder DATE_SPACING = textSpacing(2, 8f).space(7f).chars(2, 8f).space(7f).chars(
            4,
            8f);

}
