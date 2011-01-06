package elekto.results.model;

/**
 * Calculates which {@link Candidat} is elected for the {@link Election}s of an {@link Operation}.
 */
public interface Resultor {

    void calculateResults(
            Operation operation);

}
