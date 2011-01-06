package elekto.results.model;

import java.util.Date;

import elekto.util.Builder;

public class CandidatBuilder
        implements Builder<Candidat> {

    /**
     * Instantiates, beware that this instance must not call {@link #endCandidat()} to prevent
     * {@link NullPointerException} .
     * 
     * @see #endCandidat()
     */
    public CandidatBuilder()
    {
        this(null);
    }


    /**
     * Instantiates with parent {@link ListeBuilder}.
     * 
     * @param parentBuilder
     *        the parent {@link ListeBuilder}, may be null if not calling {@link #endCandidat()}.
     */
    CandidatBuilder(
            final ListeBuilder parentBuilder)
    {
        this.parentBuilder = parentBuilder;
    }


    public CandidatBuilder nom(
            final String nom)
    {
        this.nom = nom;

        return this;
    }


    public CandidatBuilder prenom(
            final String prenom)
    {
        this.prenom = prenom;

        return this;
    }


    public CandidatBuilder dateNaissance(
            final Date naissanceDate)
    {
        this.naissanceDate = naissanceDate;

        return this;
    }


    public CandidatBuilder sexe(
            final Sexe sexe)
    {
        this.sexe = sexe;

        return this;
    }


    public CandidatBuilder voixCount(
            final int voixCount)
    {
        this.voixCount = voixCount;

        return this;
    }


    /**
     * Returns the parent {@link ListeBuilder}, {@link #CandidatBuilder(ListeBuilder)} must have been
     * initialized with a not null parent.
     * 
     * @return the parent {@link ListeBuilder}.
     * 
     * @throws NullPointerException
     *         if {@link #CandidatBuilder(ListeBuilder)} have been
     *         initialized with a null parent.
     */
    public ListeBuilder endCandidat()
    {
        this.parentBuilder.addCandidat(this.toCandidat());

        return this.parentBuilder;
    }


    public Candidat toCandidat()
    {
        final Candidat candidat = new Candidat(this.nom, this.prenom, this.naissanceDate, this.sexe);
        candidat.setVoixCount(this.voixCount);

        return candidat;
    }

    private final ListeBuilder parentBuilder;

    private String nom;

    private String prenom;

    private Date naissanceDate;

    private Sexe sexe;

    private int voixCount;

}
