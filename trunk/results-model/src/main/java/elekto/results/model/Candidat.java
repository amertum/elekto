package elekto.results.model;

import static elekto.util.EqualsBuilderUtils.isEqualsCalculable;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.time.DateFormatUtils;

public class Candidat {

    Candidat(
            final String nom,
            final String prenom,
            final Date naissanceDate,
            final Sexe sexe)
    {
        this.nom = nom;
        this.prenom = prenom;
        this.naissanceDate = naissanceDate;
        this.sexe = sexe;
    }


    public String getNom()
    {
        return this.nom;
    }


    public String getPrenom()
    {
        return this.prenom;
    }


    public Date getNaissanceDate()
    {
        return this.naissanceDate;
    }


    public Sexe getSexe()
    {
        return this.sexe;
    }


    public int getVoixCount()
    {
        return this.voixCount;
    }


    public void setVoixCount(
            final int voixCount)
    {
        this.voixCount = voixCount;
    }


    public boolean isElu()
    {
        return this.elu;
    }


    void setElu(
            final boolean elu)
    {
        this.elu = elu;
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
        final Candidat rhs = (Candidat) obj;

        final EqualsBuilder builder = new EqualsBuilder();
        builder.append(this.getNom(), rhs.getNom());
        builder.append(this.getPrenom(), rhs.getPrenom());
        builder.append(this.getNaissanceDate(), rhs.getNaissanceDate());
        builder.append(this.getSexe(), rhs.getSexe());

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

        builder.append(this.getNom());
        builder.append(this.getPrenom());
        builder.append(this.getNaissanceDate());
        builder.append(this.getSexe());

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

        builder.append("nom", this.getNom());
        builder.append("prenom", this.getPrenom());
        builder.append("naissanceDate", (this.getNaissanceDate() == null)
                ? null
                : DateFormatUtils.format(this.getNaissanceDate(), TOUR_DATE_PATTERN));
        builder.append("sexe", this.getSexe());
        builder.append("voixCount", this.getVoixCount());

        final String toString = builder.toString();
        return toString;
    }

    private static final String TOUR_DATE_PATTERN = "dd/MM/yyyy";

    private final String nom;

    private final String prenom;

    private final Date naissanceDate;

    private final Sexe sexe;

    // provided results

    private int voixCount;

    // calculated results

    private boolean elu;

}
