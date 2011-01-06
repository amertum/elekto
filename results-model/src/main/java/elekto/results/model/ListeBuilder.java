package elekto.results.model;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Collection;

import elekto.util.Builder;

public class ListeBuilder
        implements Builder<Liste> {

    /**
     * Instantiates, beware that this instance must not call {@link #endListe()} to prevent {@link NullPointerException}
     * .
     * 
     * @see #endListe()
     */
    public ListeBuilder()
    {
        this(null);
    }


    /**
     * Instantiates with parent {@link ElectionBuilder}.
     * 
     * @param parentBuilder
     *        the parent {@link ElectionBuilder}, may be null if not calling {@link #endListe()}.
     */
    public ListeBuilder(
            final ElectionBuilder parentBuilder)
    {
        this.parentBuilder = parentBuilder;
    }


    public ListeBuilder nom(
            final String nom)
    {
        this.nom = nom;

        return this;
    }


    public ListeBuilder bulletinsValablesCount(
            final int bulletinsValablesCount)
    {
        this.bulletinsValablesCount = bulletinsValablesCount;

        return this;
    }


    public CandidatBuilder addCandidat()
    {
        return new CandidatBuilder(this);
    }


    ListeBuilder addCandidat(
            final Candidat candidat)
    {
        this.candidats.add(candidat);

        return this;
    }


    /**
     * Returns the parent {@link ElectionBuilder}, {@link #ListeBuilder(ElectionBuilder)} must have been
     * initialized with a not null parent.
     * 
     * @return the parent {@link ElectionBuilder}.
     * 
     * @throws NullPointerException
     *         if {@link #ListeBuilder(ElectionBuilder)} have been
     *         initialized with a null parent.
     */
    public ElectionBuilder endListe()
    {
        this.parentBuilder.addListe(this.toListe());

        return this.parentBuilder;
    }


    public Liste toListe()
    {
        final Liste liste = new Liste(this.nom, this.candidats);
        liste.setBulletinsValablesCount(this.bulletinsValablesCount);

        return liste;
    }

    private final ElectionBuilder parentBuilder;

    private String nom;

    private final Collection<Candidat> candidats = newArrayList();

    private int bulletinsValablesCount;

}
