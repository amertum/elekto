package elekto.results.cerfa.core;

import static elekto.results.model.Sexe.HOMME;

import java.util.Date;

import org.junit.Test;

import elekto.results.model.Categorie;
import elekto.results.model.Operation;
import elekto.results.model.OperationBuilder;
import elekto.results.model.Type;

/**
 * Unit test of {@link OperationElectionModelFactory}.
 */
public class OperationElectionModelFactoryTest {

    @Test
    public final void testCreate()
    {
        final Operation operation = makeOperation();
        final OperationElectionModelFactory factory = new OperationElectionModelFactory();
        factory.create(operation);
    }


    private static Operation makeOperation()
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

        return operation;
    }

}
