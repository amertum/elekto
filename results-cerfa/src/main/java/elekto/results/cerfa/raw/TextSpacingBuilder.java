package elekto.results.cerfa.raw;

import static java.util.Collections.emptyList;
import static org.apache.commons.lang.StringUtils.mid;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Construit un {@link Iterable} de {@link TextSpacing} définissant l'espace occupé par un groupe de caractère.
 */
class TextSpacingBuilder {

    public Iterable<TextSpacing> build(
            final String text)
    {
        if (text.isEmpty()) {
            return emptyList();
        }

        final Collection<TextSpacing> spacings = new ArrayList<TextSpacing>();

        int index = 0;
        for (final TextSpacing textSpacing : this.textSpacings) {
            final TextSpacing spacing = new TextSpacing(textSpacing);
            spacings.add(spacing);

            if (spacing.getCount() == 0) {
                spacing.setText(" ");
                spacing.setCount(1);
            }
            else {
                final String textPart = mid(text, index, spacing.getCount());
                spacing.setText(textPart);

                index += spacing.getCount();
            }
        }

        return spacings;
    }


    public TextSpacingBuilder chars(
            final int count,
            final float spacing)
    {
        this.textSpacings.add(new TextSpacing(count, spacing));

        return this;
    }


    public TextSpacingBuilder space(
            final float spacing)
    {
        this.textSpacings.add(new TextSpacing(0, spacing));

        return this;
    }


    public static TextSpacingBuilder textSpacing(
            final int count,
            final float spacing)
    {
        return new TextSpacingBuilder().chars(count, spacing);
    }

    private final Collection<TextSpacing> textSpacings = new ArrayList<TextSpacing>();

}
