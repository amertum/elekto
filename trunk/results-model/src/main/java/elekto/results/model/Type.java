package elekto.results.model;

public enum Type
        implements ElectionProperty {

    NONE(""),
    COMITE_ENTREPRISE("CE"),
    DELEGUE_DU_PERSONNEL("DP"),
    DELEGATION_UNIQUE_DU_PERSONNEL("DUP");

    Type(
            final String code)
    {
        this.code = code;
    }


    public String getCode()
    {
        return this.code;
    }

    private final String code;


    public static Type forCode(
            final String code)
    {
        for (final Type value : Type.values()) {
            final boolean matches = value.getCode().equals(code);
            if (matches) {
                return value;
            }
        }

        return Type.NONE;
    }

}
