package elekto.results.model;

import static com.google.common.collect.Iterables.get;
import static com.google.common.collect.Iterables.getOnlyElement;
import static com.google.common.collect.Iterables.size;
import static elekto.results.model.Sexe.HOMME;
import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;

/**
 * Unit test of {@link OperationBuilder}.
 */
public class OperationBuilderTest {

    @Test
    public void testBuildOperation()
    {
        final Date date = new Date();
// @formatter:off
        final Operation operation = new OperationBuilder()
            .addElection()
                // electionBuilder
                .type(Type.COMITE_ENTREPRISE)
                .categorie(Categorie.TITULAIRES)
                .etablissement("raison sociale", "adresse1", "adresse2", "code postal", "ville", "siret", "idcc")
                .college("Employés", "Cadres")
                .tour(1, date)
                .scrutinPrecedentDate(date)
                .partielle()
                .electeursInscritsCount(1000)
                .electeursVotantsCount(800)
                .bulletinsBlancsOuNulsCount(200)
                .siegesCount(2)
                .addListe()
                    // listeBuilder
                    .nom("CFDT")
                    .bulletinsValablesCount(50)
                    .addCandidat()
                        // candidatBuilder
                        .nom("nom")
                        .prenom("prenom")
                        .dateNaissance(date)
                        .sexe(HOMME)
                        .voixCount(5)
                    .endCandidat()
                .endListe()
                .addListe()
                    // listeBuilder
                    .nom("CFTC")
                    .bulletinsValablesCount(50)
                    .addCandidat()
                        // candidatBuilder
                        .nom("nom1")
                        .prenom("prenom1")
                        .dateNaissance(date)
                        .sexe(HOMME)
                        .voixCount(5)
                    .endCandidat()
                    .addCandidat()
                        // candidatBuilder
                        .nom("nom2")
                        .prenom("prenom2")
                        .dateNaissance(date)
                        .sexe(HOMME)
                        .voixCount(5)
                    .endCandidat()
                .endListe()
            .endElection()
            .toOperation();
// @formatter:on

        // TODO Date today = new Date();
        // TODO operation.getCurrentTour(today);
        final Election election = getOnlyElement(operation.getElections());
        assertThat(election.getKey().getType()).isEqualTo(Type.COMITE_ENTREPRISE);
        assertThat(election.getKey().getCategorie()).isEqualTo(Categorie.TITULAIRES);
        assertThat(election.getKey().getEtablissement().getRaisonSociale()).isEqualTo("raison sociale");
        assertThat(election.getKey().getEtablissement().getAdresse1()).isEqualTo("adresse1");
        assertThat(election.getKey().getEtablissement().getAdresse2()).isEqualTo("adresse2");
        assertThat(election.getKey().getEtablissement().getCodePostal()).isEqualTo("code postal");
        assertThat(election.getKey().getEtablissement().getVille()).isEqualTo("ville");
        assertThat(election.getKey().getEtablissement().getSiret()).isEqualTo("siret");
        assertThat(election.getKey().getEtablissement().getIdcc()).isEqualTo("idcc");
        assertThat(election.getKey().getCollege().getCategories()).containsOnly("Employés", "Cadres");
        assertThat(election.getKey().getTour().getIndex()).isEqualTo(1);
        assertThat(election.getKey().getTour().getDate()).isEqualTo(date);

        assertThat(election.getScrutinPrecedentDate()).isEqualTo(date);
        assertThat(election.isPartielle()).isTrue();
        assertThat(election.getElecteursInscritsCount()).isEqualTo(1000);
        assertThat(election.getElecteursVotantsCount()).isEqualTo(800);
        assertThat(election.getBulletinsBlancsOuNulsCount()).isEqualTo(200);
        assertThat(election.getSiegesCount()).isEqualTo(2);

        assertThat(size(election.getListes())).isEqualTo(2);
        {
            final Liste liste = get(election.getListes(), 0);
            assertThat(liste.getNom()).isEqualTo("CFDT");
            assertThat(liste.getBulletinsValablesCount()).isEqualTo(50);

            final Candidat candidat = getOnlyElement(liste.getCandidats());
            assertThat(candidat.getNom()).isEqualTo("nom");
            assertThat(candidat.getPrenom()).isEqualTo("prenom");
            assertThat(candidat.getNaissanceDate()).isEqualTo(date);
            assertThat(candidat.getSexe()).isEqualTo(Sexe.HOMME);
            assertThat(candidat.getVoixCount()).isEqualTo(5);
        }
        {
            final Liste liste = get(election.getListes(), 1);
            assertThat(liste.getNom()).isEqualTo("CFTC");
            assertThat(liste.getBulletinsValablesCount()).isEqualTo(50);

            {
                final Candidat candidat = get(liste.getCandidats(), 0);
                assertThat(candidat.getNom()).isEqualTo("nom1");
                assertThat(candidat.getPrenom()).isEqualTo("prenom1");
                assertThat(candidat.getNaissanceDate()).isEqualTo(date);
                assertThat(candidat.getSexe()).isEqualTo(Sexe.HOMME);
                assertThat(candidat.getVoixCount()).isEqualTo(5);
            }
            {
                final Candidat candidat = get(liste.getCandidats(), 1);
                assertThat(candidat.getNom()).isEqualTo("nom2");
                assertThat(candidat.getPrenom()).isEqualTo("prenom2");
                assertThat(candidat.getNaissanceDate()).isEqualTo(date);
                assertThat(candidat.getSexe()).isEqualTo(Sexe.HOMME);
                assertThat(candidat.getVoixCount()).isEqualTo(5);
            }
        }
    }

}
