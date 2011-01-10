package elekto.results.model;

import java.util.Date;
import java.util.NoSuchElementException;

import com.google.common.collect.ImmutableList;

/**
 */
public class Operation {

    Operation(
            final Iterable<Election> elections)
    {
        this.elections = ImmutableList.copyOf(elections);
    }


    /**
     * @param electionKey
     * 
     * @return the election that matches {@link ElectionKey}.
     * 
     * @throws NoSuchElementException
     */
    public Election findElection(
            final ElectionKey electionKey)
    {
        return null;
    }


    public Iterable<Election> searchElections(
            final Iterable<ElectionProperty> searchProperties)
    {
        return null;
    }


    /**
     * @return all elections ordered by {@code tour.index}, {@code election.type}, {@code election.categorie},
     *         {@code election.etablissement}, {@code election.college}.
     */
    public Iterable<Election> getElections()
    {
        return this.elections;
    }


    public Tour getCurrentTour()
    {
        return new Tour(1, new Date());
    }


    /**
     * validate les incohérences comme :
     * <ul>
     * <li>le nombre de délégués du personnel en fonction du nombre de votant.
     * <li>Les listes ne doivent pas contenir plus de candidats que de sièges à pourvoir.
     * <li>une liste doit contenir des candidats
     * </ul>
     */
    public void validate()
    {
        // TODO
    }


    /**
     * Calculates which {@link Candidat} is elected for the {@link Election}s of this {@link Operation}.
     * 
     * @param resultor
     *        the {@link Resultor} to which delegate the calculation.
     */
    public void calculateResults(
            final Resultor resultor)
    {
        resultor.calculateResults(this);
    }

    private final Iterable<Election> elections;

}
