package elekto.results.model;

import static ch.lambdaj.Lambda.forEach;
import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.select;
import static ch.lambdaj.Lambda.sumFrom;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.hamcrest.core.IsEqual.equalTo;

import com.google.common.base.Predicate;

public class CedpResultor
        implements Resultor {

    @Override
    public void calculateResults(
            final Operation operation)
    {
        for (final Election election : operation.getElections()) {
            new CedpElectionCalculator(election).calculate();

            if (election.isQuorumAtteint()) {
                for (final Liste liste : election.getListes()) {
                    new CedpListeCalculator(election, liste).calculate();
                }
            }
        }
    }

    static class CedpListeCalculator {

        CedpListeCalculator(
                final Election election,
                final Liste liste)
        {
            this.election = election;
            this.liste = liste;
        }


        public void calculate()
        {
            this.calculateQuotient();
            this.calculatePlusforteMoyenne();
        }


        private void calculateQuotient()
        {
            final int candidatVoixCount = sumFrom(this.liste.getCandidats(), Candidat.class).getVoixCount();
            this.liste.setCandidatsVoixCount(candidatVoixCount);

            final float voixMoyenne = this.liste.getCandidatsVoixCount() / (float) this.liste.getCandidatsCount();
            this.liste.setVoixMoyenne(voixMoyenne);

            final float siegesAttribuesQuotient = voixMoyenne / this.election.getQuotientElectoral();
            this.liste.setSiegesAttribuesQuotient(siegesAttribuesQuotient);

            final int siegesAttribues = (int) Math.floor(siegesAttribuesQuotient);
            final Iterable<Candidat> candidats = select(
                    this.liste.getCandidats(),
                    having(on(Candidat.class).isElu(), equalTo(FALSE)));
            forEach(candidats, Candidat.class).setElu(true);
        }


        private void calculatePlusforteMoyenne()
        {
            this.liste.setSiegesAttribuesPlusForteMoyenneSiege1(0);
            this.liste.setSiegesAttribuesPlusForteMoyenneSiege2(0);
            this.liste.setSiegesAttribuesPlusForteMoyenneSiege3(0);

            // TODO after candidat were set elu
            final int elusCount = select(this.liste.getCandidats(), having(on(Candidat.class).isElu(), equalTo(TRUE))).size();
            this.liste.setElusCount(elusCount);
        }

        private final Election election;

        private final Liste liste;

    }

    /**
     * {@link Predicate} which returns true if the {@link Candidat} can be elected, false else.
     */
    static class EligibleCandidatPredicate
            implements Predicate<Candidat> {

        EligibleCandidatPredicate(
                final Liste liste)
        {
            this.liste = liste;
        }


        /**
         * {@inheritDoc}
         * 
         * @see com.google.common.base.Predicate#apply(java.lang.Object)
         */
        @Override
        public boolean apply(
                final Candidat candidat)
        {
            final boolean listeSansVoix = (this.liste.getBulletinsValablesCount() == 0);
            final boolean titulaireConsidereElu = false;

            final boolean eligible = !candidat.isElu() && !titulaireConsidereElu && !listeSansVoix;

            return eligible;
        }

        private final Liste liste;

    }

}
