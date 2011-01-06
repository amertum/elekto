package elekto.results.model;

import static ch.lambdaj.Lambda.collect;
import static ch.lambdaj.Lambda.on;

class CedpElectionCalculator {

    CedpElectionCalculator(
            final Election election)
    {
        this.election = election;
    }


    public void calculate()
    {
        this.calculateSuffragesValablesCount();
        this.calculateCarence();
        this.calculateQuorum();
        this.calculateQuorumAtteint();
        this.calculateQuotientElectoral();
    }


    private void calculateSuffragesValablesCount()
    {
        this.election.setSuffragesValablesCount(this.election.getElecteursVotantsCount()
                - this.election.getBulletinsBlancsOuNulsCount());
    }


    /**
     * Calculate if the election has a carence of candidat.
     * <p>
     * ie: if there is no liste or listes have no candidat.
     */
    private void calculateCarence()
    {
        final boolean carence = collect(this.election.getListes(), on(Liste.class).getCandidats()).isEmpty();
        this.election.setCarence(carence);
    }


    private void calculateQuorum()
    {
        final float quorum = this.election.getElecteursInscritsCount() / 2f;
        this.election.setQuorum(quorum);
    }


    private void calculateQuorumAtteint()
    {
        final boolean quorumAtteint = this.election.getSuffragesValablesCount() >= this.election.getQuorum();
        this.election.setQuorumAtteint(quorumAtteint);
    }


    private void calculateQuotientElectoral()
    {
        final float quotientElectoral = this.election.getSuffragesValablesCount()
                / (float) this.election.getSiegesCount();
        this.election.setQuotientElectoral(quotientElectoral);
    }

    private final Election election;

}
