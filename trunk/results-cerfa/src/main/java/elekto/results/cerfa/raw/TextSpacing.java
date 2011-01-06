package elekto.results.cerfa.raw;

/**
 * Définit l'espace occupé par chaque caractère pour un groupe de caractère.
 */
class TextSpacing {

    TextSpacing(
            final int count,
            final float spacing)
    {
        this.count = count;
        this.spacing = spacing;
    }


    TextSpacing(
            final TextSpacing textSpacing)
    {
        this.count = textSpacing.getCount();
        this.spacing = textSpacing.getSpacing();
    }


    public int getCount()
    {
        return this.count;
    }


    public float getSpacing()
    {
        return this.spacing;
    }


    public String getText()
    {
        return this.text;
    }


    public void setCount(
            final int count)
    {
        this.count = count;
    }


    void setText(
            final String text)
    {
        this.text = text;
    }

    private int count;

    private final float spacing;

    private String text;

}
