package elekto.results.model;

import static elekto.util.EqualsBuilderUtils.isEqualsCalculable;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.time.DateFormatUtils;

/**
 * 
 */
public class Tour
        implements ElectionProperty {

    public Tour(
            final int index,
            final Date date)
    {
        this.index = index;
        // TODO test defensive
        this.date = new Date(date.getTime());
    }


    public int getIndex()
    {
        return this.index;
    }


    public Date getDate()
    {
        // TODO test defensive
        return new Date(this.date.getTime());
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
        final Tour rhs = (Tour) obj;

        final EqualsBuilder builder = new EqualsBuilder();
        builder.append(this.getIndex(), rhs.getIndex());
        builder.append(this.getDate(), rhs.getDate());

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

        builder.append(this.getIndex());
        builder.append(this.getDate());

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

        builder.append("index", this.getIndex());
        builder.append("date", DateFormatUtils.format(this.getDate(), TOUR_DATE_PATTERN));

        final String toString = builder.toString();
        return toString;
    }

    private static final String TOUR_DATE_PATTERN = "dd/MM/yyyy";

    private final int index;

    private final Date date;

}
