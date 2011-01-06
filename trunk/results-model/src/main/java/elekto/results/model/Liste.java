package elekto.results.model;

import static com.google.common.collect.Iterables.size;
import static elekto.util.EqualsBuilderUtils.isEqualsCalculable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.common.collect.ImmutableList;

public class Liste {

    Liste(
            final String nom,
            final Iterable<Candidat> candidats)
    {
        this.nom = nom;
        this.candidats = ImmutableList.copyOf(candidats);
    }


    public String getNom()
    {
        return this.nom;
    }


    public Iterable<Candidat> getCandidats()
    {
        return this.candidats;
    }


    public int getBulletinsValablesCount()
    {
        return this.bulletinsValablesCount;
    }


    public void setBulletinsValablesCount(
            final int bulletinsValablesCount)
    {
        this.bulletinsValablesCount = bulletinsValablesCount;
    }


    public int getCandidatsVoixCount()
    {
        return this.candidatsVoixCount;
    }


    void setCandidatsVoixCount(
            final int candidatsVoixCount)
    {
        this.candidatsVoixCount = candidatsVoixCount;
    }


    public int getCandidatsCount()
    {
        return size(this.candidats);
    }


    public float getVoixMoyenne()
    {
        return this.voixMoyenne;
    }


    void setVoixMoyenne(
            final float voixMoyenne)
    {
        this.voixMoyenne = voixMoyenne;
    }


    public float getSiegesAttribuesQuotient()
    {
        return this.siegesAttribuesQuotient;
    }


    void setSiegesAttribuesQuotient(
            final float siegesAttribuesQuotient)
    {
        this.siegesAttribuesQuotient = siegesAttribuesQuotient;
    }


    public float getSiegesAttribuesPlusForteMoyenneSiege1()
    {
        return this.siegesAttribuesPlusForteMoyenneSiege1;
    }


    void setSiegesAttribuesPlusForteMoyenneSiege1(
            final float siegesAttribuesPlusForteMoyenneSiege1)
    {
        this.siegesAttribuesPlusForteMoyenneSiege1 = siegesAttribuesPlusForteMoyenneSiege1;
    }


    public float getSiegesAttribuesPlusForteMoyenneSiege2()
    {
        return this.siegesAttribuesPlusForteMoyenneSiege2;
    }


    void setSiegesAttribuesPlusForteMoyenneSiege2(
            final float siegesAttribuesPlusForteMoyenneSiege2)
    {
        this.siegesAttribuesPlusForteMoyenneSiege2 = siegesAttribuesPlusForteMoyenneSiege2;
    }


    public float getSiegesAttribuesPlusForteMoyenneSiege3()
    {
        return this.siegesAttribuesPlusForteMoyenneSiege3;
    }


    void setSiegesAttribuesPlusForteMoyenneSiege3(
            final float siegesAttribuesPlusForteMoyenneSiege3)
    {
        this.siegesAttribuesPlusForteMoyenneSiege3 = siegesAttribuesPlusForteMoyenneSiege3;
    }


    public int getElusCount()
    {
        return this.elusCount;
    }


    void setElusCount(
            final int elusCount)
    {
        this.elusCount = elusCount;
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
        final Liste rhs = (Liste) obj;

        final EqualsBuilder builder = new EqualsBuilder();
        builder.append(this.getNom(), rhs.getNom());
        builder.append(this.getCandidats(), rhs.getCandidats());

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
        builder.append(this.getCandidats());

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
        builder.append("bulletinsValablesCount", this.getBulletinsValablesCount());
        builder.append("candidats", this.getCandidats());

        final String toString = builder.toString();
        return toString;
    }

    private final String nom;

    private final Iterable<Candidat> candidats;

    // provided results

    private int bulletinsValablesCount;

    // calculated results

    private int candidatsVoixCount;

    private int candidatsCount;

    private float voixMoyenne;

    private float siegesAttribuesQuotient;

    private float siegesAttribuesPlusForteMoyenneSiege1;

    private float siegesAttribuesPlusForteMoyenneSiege2;

    private float siegesAttribuesPlusForteMoyenneSiege3;

    private int elusCount;

}
