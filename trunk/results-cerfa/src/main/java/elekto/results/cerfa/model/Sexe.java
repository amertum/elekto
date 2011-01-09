package elekto.results.cerfa.model;

/**
 * Enumération des sexes possibles.
 */
public enum Sexe {

    /**
     * Sexe non précisé, {@code code: ""}.
     */
    NONE(""),

    /**
     * Sexe masculin, {@code code: "H"}.
     */
    HOMME("H"),

    /**
     * Sexe féminin, {@code code: "F"}.
     */
    FEMME("F");

    Sexe(
            final String code)
    {
        this.code = code;
    }


    public String getCode()
    {
        return this.code;
    }


    @Override
    public String toString()
    {
        return this.code;
    }

    private final String code;

}
