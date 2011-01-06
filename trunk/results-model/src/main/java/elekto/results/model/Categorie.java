package elekto.results.model;

public enum Categorie
        implements ElectionProperty {

    NONE(""),
    TITULAIRES("Titulaires"),
    SUPPLEANTS("Suppleants");

    Categorie(
            final String code)
    {
        this.code = code;
    }


    public String getCode()
    {
        return this.code;
    }

    private final String code;


    public static Categorie forCode(
            final String code)
    {
        for (final Categorie value : Categorie.values()) {
            final boolean matches = value.getCode().equals(code);
            if (matches) {
                return value;
            }
        }

        return Categorie.NONE;
    }

}
