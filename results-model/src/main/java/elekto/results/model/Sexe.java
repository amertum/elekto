package elekto.results.model;

public enum Sexe {

    NONE(""),
    HOMME("H"),
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

    private final String code;

}
