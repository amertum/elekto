package elekto.results.model;

import static elekto.util.EqualsBuilderUtils.isEqualsCalculable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

public class College
        implements ElectionProperty {

    College(
            final Denomination denomination,
            final Iterable<CollegeEnum> categories)
    {
        this.denomination = denomination;
        this.categories = ImmutableList.copyOf(categories);
    }


    public Iterable<CollegeEnum> getCategories()
    {
        return this.categories;
    }


    public Denomination getDenomination()
    {
        return this.denomination;
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
        final College rhs = (College) obj;

        final EqualsBuilder builder = new EqualsBuilder();
        builder.appendSuper(Iterables.elementsEqual(this.getCategories(), rhs.getCategories()));

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

        builder.append(this.getCategories());

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

        builder.append("categories", this.getCategories());

        final String toString = builder.toString();
        return toString;
    }

    public enum Denomination {
        UNIQUE,
        PREMIER,
        DEUXIEME,
        TROISIEME,
        AUTRE, ;
    }

    public enum CollegeEnum {
        OUVRIERS,
        EMPLOYES,
        TECHNICIENS,
        AGENTS_DE_MAITRISE,
        INGENIEURS,
        CADRES,
        AUTRES, ;
    }

    public static class CollegeEnumFunction
            implements Function<String, CollegeEnum> {

        @Override
        public CollegeEnum apply(
                final String input)
        {
            return CollegeEnum.valueOf(input);
        }

    }

    private final Denomination denomination;

    private final Iterable<CollegeEnum> categories;

}
