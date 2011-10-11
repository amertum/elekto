package elekto.results.model;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Collection;
import java.util.Date;

import elekto.results.model.College.Denomination;
import elekto.util.Builder;

public class ElectionBuilder
        implements Builder<Election> {

    /**
     * Instantiates, beware that this instance must not call {@link #endElection()} to prevent
     * {@link NullPointerException}.
     * 
     * @see #endElection()
     */
    public ElectionBuilder()
    {
        this(null);
    }


    /**
     * Instantiates with parent {@link OperationBuilder}.
     * 
     * @param parentBuilder
     *        the parent {@link OperationBuilder}, may be null if not calling {@link #endElection()}.
     */
    public ElectionBuilder(
            final OperationBuilder parentBuilder)
    {
        this.parentBuilder = parentBuilder;
    }


    public ElectionBuilder type(
            final Type type)
    {
        this.type = type;

        return this;
    }


    public ElectionBuilder categorie(
            final Categorie categorie)
    {
        this.categorie = categorie;

        return this;
    }


    public ElectionBuilder etablissement(
            final String raisonSociale,
            final String adresse1,
            final String adresse2,
            final String codePostal,
            final String ville,
            final String siret,
            final String idcc)
    {
        // TODO ? use factory to retrieve the same etablissement, apply this for other class
        this.etablissement = new Etablissement(raisonSociale, adresse1, adresse2, codePostal, ville, siret, idcc);

        return this;
    }


    public ElectionBuilder college(
            final Denomination denomination,
            final Iterable<College.CollegeEnum> collegeCategories)
    {
        // TODO ? use factory to retrieve the same college, apply this for other class
        this.college = new College(denomination, newArrayList(collegeCategories));

        return this;
    }


    public ElectionBuilder college(
            final Denomination denomination,
            final College.CollegeEnum... colleges)
    {
        // TODO ? use factory to retrieve the same college, apply this for other class
        this.college = new College(denomination, newArrayList(colleges));

        return this;
    }


    public ElectionBuilder tour(
            final int index,
            final Date tourDate)
    {
        // TODO ? use factory to retrieve the same tour, apply this for other class
        this.tour = new Tour(index, tourDate);

        return this;
    }


    public ElectionBuilder scrutinPrecedentDate(
            final Date scrutinPrecedentDate)
    {
        this.scrutinPrecedentDate = scrutinPrecedentDate;

        return this;
    }


    public ElectionBuilder partielle()
    {
        this.partielle = true;

        return this;
    }


    public ElectionBuilder electeursInscritsCount(
            final int electeursInscritsCount)
    {
        this.electeursInscritsCount = electeursInscritsCount;

        return this;
    }


    public ElectionBuilder electeursVotantsCount(
            final int electeursVotantsCount)
    {
        this.electeursVotantsCount = electeursVotantsCount;

        return this;
    }


    public ElectionBuilder bulletinsBlancsOuNulsCount(
            final int bulletinsBlancsOuNulsCount)
    {
        this.bulletinsBlancsOuNulsCount = bulletinsBlancsOuNulsCount;

        return this;
    }


    public ElectionBuilder siegesCount(
            final int siegesCount)
    {
        this.siegesCount = siegesCount;

        return this;
    }


    public ListeBuilder addListe()
    {
        return new ListeBuilder(this);
    }


    ElectionBuilder addListe(
            final Liste liste)
    {
        this.listes.add(liste);

        return this;
    }


    /**
     * Returns the parent {@link OperationBuilder}, {@link #ElectionBuilder(OperationBuilder)} must have been
     * initialized with a not null parent.
     * 
     * @return the parent {@link OperationBuilder}.
     * 
     * @throws NullPointerException
     *         if {@link #ElectionBuilder(OperationBuilder)} have been
     *         initialized with a null parent.
     */
    public OperationBuilder endElection()
    {
        final Election election = this.toElection();
        this.parentBuilder.addElection(election);

        return this.parentBuilder;
    }


    public Election toElection()
    {
        // TODO reuse election if no changes
        final ElectionKey electionKey = new ElectionKey(
                this.tour,
                this.type,
                this.categorie,
                this.etablissement,
                this.college);
        final Election election = new Election(
                electionKey,
                this.listes,
                this.scrutinPrecedentDate,
                this.siegesCount,
                this.partielle,
                this.electeursInscritsCount);
        election.setElecteursVotantsCount(this.electeursVotantsCount);
        election.setBulletinsBlancsOuNulsCount(this.bulletinsBlancsOuNulsCount);

        return election;
    }

    private final OperationBuilder parentBuilder;

    private Type type;

    private Categorie categorie;

    private Etablissement etablissement;

    private College college;

    private Tour tour;

    private final Collection<Liste> listes = newArrayList();

    private Date scrutinPrecedentDate;

    private int siegesCount;

    private boolean partielle;

    private int electeursInscritsCount;

    private int electeursVotantsCount;

    private int bulletinsBlancsOuNulsCount;

}
