package elekto.results.model;

import static elekto.util.EqualsBuilderUtils.isEqualsCalculable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * An {@code ElectionKey} is composed of multiple informations which uniquely define an Election.
 */
public class ElectionKey {

    ElectionKey(
            final Tour tour,
            final Type type,
            final Categorie categorie,
            final Etablissement etablissement,
            final College college)
    {
        this.tour = tour;
        this.type = type;
        this.categorie = categorie;
        this.etablissement = etablissement;
        this.college = college;
    }


    public Tour getTour()
    {
        return this.tour;
    }


    public Type getType()
    {
        return this.type;
    }


    public Categorie getCategorie()
    {
        return this.categorie;
    }


    public Etablissement getEtablissement()
    {
        return this.etablissement;
    }


    public College getCollege()
    {
        return this.college;
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
        final ElectionKey rhs = (ElectionKey) obj;

        final EqualsBuilder builder = new EqualsBuilder();
        builder.append(this.getTour(), rhs.getTour());
        builder.append(this.getType(), rhs.getType());
        builder.append(this.getCategorie(), rhs.getCategorie());
        builder.append(this.getEtablissement(), rhs.getEtablissement());
        builder.append(this.getCollege(), rhs.getCollege());

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

        builder.append(this.getTour());
        builder.append(this.getType());
        builder.append(this.getCategorie());
        builder.append(this.getEtablissement());
        builder.append(this.getCollege());

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

        builder.append("tour", this.getTour());
        builder.append("type", this.getType());
        builder.append("categorie", this.getCategorie());
        builder.append("etablissement", this.getEtablissement());
        builder.append("college", this.getCollege());

        final String toString = builder.toString();
        return toString;
    }

    private final Tour tour;

    private final Type type;

    private final Categorie categorie;

    private final Etablissement etablissement;

    private final College college;

}
