package elekto.results.model;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * Unit test of {@link CedpElectionCalculator}.
 */
public class CedpElectionResultorTest {

    @Test
    public final void testCalculateWithNoListe()
    {
//@formatter:off
        final Election election = new ElectionBuilder()
            .electeursInscritsCount(100)
            .electeursVotantsCount(80)
            .bulletinsBlancsOuNulsCount(10)
            .siegesCount(5)
        .toElection();
//@formatter:on

        new CedpElectionCalculator(election).calculate();

        assertThat(election.getSuffragesValablesCount()).isEqualTo(70);
        assertThat(election.isCarence()).isTrue();
        assertThat(election.getQuorum()).isEqualTo(50f);
        assertThat(election.isQuorumAtteint()).isTrue();
        assertThat(election.getQuotientElectoral()).isEqualTo(14f);
    }


    @Test
    public final void testCalculateWithNoCandidat()
    {
//@formatter:off
        final Election election = new ElectionBuilder()
            .electeursInscritsCount(100)
            .electeursVotantsCount(60)
            .bulletinsBlancsOuNulsCount(10)
            .siegesCount(5)
            .addListe().endListe()
            .addListe().endListe()
        .toElection();
//@formatter:on

        new CedpElectionCalculator(election).calculate();

        assertThat(election.getSuffragesValablesCount()).isEqualTo(50);
        assertThat(election.isCarence()).isTrue();
        assertThat(election.getQuorum()).isEqualTo(50f);
        assertThat(election.isQuorumAtteint()).isTrue();
        assertThat(election.getQuotientElectoral()).isEqualTo(10f);
    }


    @Test
    public final void testCalculateWithCandidats()
    {
//@formatter:off
        final Election election = new ElectionBuilder()
            .electeursInscritsCount(100)
            .electeursVotantsCount(59)
            .bulletinsBlancsOuNulsCount(10)
            .siegesCount(5)
            .addListe()
            .endListe()
            .addListe()
                .addCandidat().endCandidat()
            .endListe()
        .toElection();
//@formatter:on

        new CedpElectionCalculator(election).calculate();

        assertThat(election.getSuffragesValablesCount()).isEqualTo(49);
        assertThat(election.isCarence()).isFalse();
        assertThat(election.getQuorum()).isEqualTo(50f);
        assertThat(election.isQuorumAtteint()).isFalse();
        assertThat(election.getQuotientElectoral()).isEqualTo(9.8f);
    }
}
