package elekto.results.model;

import static elekto.util.EqualsBuilderUtils.isEqualsCalculable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Etablissement
        implements ElectionProperty {

    Etablissement(
            final String raisonSociale,
            final String adresse1,
            final String adresse2,
            final String codePostal,
            final String ville,
            final String siret,
            final String idcc)
    {
        this.raisonSociale = raisonSociale;
        this.adresse1 = adresse1;
        this.adresse2 = adresse2;
        this.codePostal = codePostal;
        this.ville = ville;
        this.siret = siret;
        this.idcc = idcc;
    }


    public String getRaisonSociale()
    {
        return this.raisonSociale;
    }


    public String getAdresse1()
    {
        return this.adresse1;
    }


    public String getAdresse2()
    {
        return this.adresse2;
    }


    public String getCodePostal()
    {
        return this.codePostal;
    }


    public String getVille()
    {
        return this.ville;
    }


    public String getSiret()
    {
        return this.siret;
    }


    public String getIdcc()
    {
        return this.idcc;
    }


    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(
            final Object obj)
    {
        if (!isEqualsCalculable(this, obj)) {
            return false;
        }
        final Etablissement rhs = (Etablissement) obj;

        final EqualsBuilder builder = new EqualsBuilder();
        builder.append(this.getRaisonSociale(), rhs.getRaisonSociale());
        builder.append(this.getAdresse1(), rhs.getAdresse1());
        builder.append(this.getAdresse2(), rhs.getAdresse2());
        builder.append(this.getCodePostal(), rhs.getCodePostal());
        builder.append(this.getVille(), rhs.getVille());
        builder.append(this.getSiret(), rhs.getSiret());
        builder.append(this.getIdcc(), rhs.getIdcc());

        final boolean equals = builder.isEquals();
        return equals;
    }


    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final HashCodeBuilder builder = new HashCodeBuilder();

        builder.append(this.getRaisonSociale());
        builder.append(this.getAdresse1());
        builder.append(this.getAdresse2());
        builder.append(this.getCodePostal());
        builder.append(this.getVille());
        builder.append(this.getSiret());
        builder.append(this.getIdcc());

        final int hashCode = builder.toHashCode();
        return hashCode;
    }


    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        final ToStringBuilder builder = new ToStringBuilder(this);

        builder.append("raisonSociale", this.getRaisonSociale());
        builder.append("adresse1", this.getAdresse1());
        builder.append("adresse2", this.getAdresse2());
        builder.append("codePostal", this.getCodePostal());
        builder.append("ville", this.getVille());
        builder.append("siret", this.getSiret());
        builder.append("idcc", this.getIdcc());

        final String toString = builder.toString();
        return toString;
    }

    private final String raisonSociale;

    private final String adresse1;

    private final String adresse2;

    private final String codePostal;

    private final String ville;

    private final String siret;

    private final String idcc;

}
