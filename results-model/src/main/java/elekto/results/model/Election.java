package elekto.results.model;

import static elekto.util.EqualsBuilderUtils.isEqualsCalculable;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.time.DateFormatUtils;

import com.google.common.collect.ImmutableList;

public class Election {

    Election(
            final ElectionKey electionKey,
            final Iterable<Liste> listes,
            final Date scrutinPrecedentDate,
            final int siegesCount,
            final boolean partielle,
            final int electeursInscritsCount)
    {
        this.electionKey = electionKey;
        this.listes = ImmutableList.copyOf(listes);
        this.scrutinPrecedentDate = scrutinPrecedentDate;
        this.siegesCount = siegesCount;
        this.partielle = partielle;
        this.electeursInscritsCount = electeursInscritsCount;
    }


    public ElectionKey getKey()
    {
        return this.electionKey;
    }


    public Iterable<Liste> getListes()
    {
        return this.listes;
    }


    public Date getScrutinPrecedentDate()
    {
        return this.scrutinPrecedentDate;
    }


    public int getSiegesCount()
    {
        return this.siegesCount;
    }


    public boolean isPartielle()
    {
        return this.partielle;
    }


    public int getElecteursInscritsCount()
    {
        return this.electeursInscritsCount;
    }


    public int getElecteursVotantsCount()
    {
        return this.electeursVotantsCount;
    }


    public void setElecteursVotantsCount(
            final int electeursVotantsCount)
    {
        this.electeursVotantsCount = electeursVotantsCount;
    }


    public int getBulletinsBlancsOuNulsCount()
    {
        return this.bulletinsBlancsOuNulsCount;
    }


    public void setBulletinsBlancsOuNulsCount(
            final int bulletinsBlancsOuNulsCount)
    {
        this.bulletinsBlancsOuNulsCount = bulletinsBlancsOuNulsCount;
    }


    public int getSuffragesValablesCount()
    {
        return this.suffragesValablesCount;
    }


    void setSuffragesValablesCount(
            final int suffragesValablesCount)
    {
        this.suffragesValablesCount = suffragesValablesCount;
    }


    public boolean isCarence()
    {
        return this.carence;
    }


    void setCarence(
            final boolean carence)
    {
        this.carence = carence;
    }


    public float getQuorum()
    {
        return this.quorum;
    }


    void setQuorum(
            final float quorum)
    {
        this.quorum = quorum;
    }


    public boolean isQuorumAtteint()
    {
        return this.isQuorumAtteint;
    }


    void setQuorumAtteint(
            final boolean isQuorumAtteint)
    {
        this.isQuorumAtteint = isQuorumAtteint;
    }


    public float getQuotientElectoral()
    {
        return this.quotientElectoral;
    }


    void setQuotientElectoral(
            final float quotientElectoral)
    {
        this.quotientElectoral = quotientElectoral;
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
        final Election rhs = (Election) obj;

        final EqualsBuilder builder = new EqualsBuilder();
        builder.append(this.getKey(), rhs.getKey());

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

        builder.append(this.getKey());

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

        builder.append("key", this.getKey());
        builder.append("scrutinPrecedentDate", (this.getScrutinPrecedentDate() == null)
                ? null
                : DateFormatUtils.format(this.getScrutinPrecedentDate(), TOUR_DATE_PATTERN));
        builder.append("partielle", this.isPartielle());
        builder.append("electeursInscritsCount", this.getElecteursInscritsCount());
        builder.append("electeursVotantsCount", this.getElecteursVotantsCount());
        builder.append("bulletinsBlancsOuNulsCount", this.getBulletinsBlancsOuNulsCount());
        builder.append("siegesCount", this.getSiegesCount());
        builder.append("listes", this.getListes());

        final String toString = builder.toString();
        return toString;
    }

    private static final String TOUR_DATE_PATTERN = "dd/MM/yyyy";

    private final ElectionKey electionKey;

    private final Iterable<Liste> listes;

    private final Date scrutinPrecedentDate;

    private final int siegesCount;

    private final boolean partielle;

    private final int electeursInscritsCount;

    // provided results

    private int electeursVotantsCount;

    private int bulletinsBlancsOuNulsCount;

    // calculated results

    private int suffragesValablesCount;

    private boolean carence;

    private float quorum;

    private boolean isQuorumAtteint;

    private float quotientElectoral;

}
