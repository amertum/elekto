package elekto.results.model;

import static elekto.results.model.Categorie.TITULAIRES;
import static elekto.results.model.Sexe.HOMME;
import static elekto.results.model.Type.COMITE_ENTREPRISE;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

/**
 * Unit test of {@link CedpResultor}.
 */
public class CedpResultorTest {

    @Test
    public final void test()
    {
    }


    private static Operation makeOperation()
        throws ParseException
    {
        final Date date = new Date();
        final Date birthDate1 = DateUtils.parseDateStrictly("05/08/1955", DATE_PATERNS);
        final Date birthDate2 = DateUtils.parseDateStrictly("05/08/1956", DATE_PATERNS);
        final Date birthDate3 = DateUtils.parseDateStrictly("05/08/1957", DATE_PATERNS);

//@formatter:off
    final Operation operation = new OperationBuilder()
        .addElection().type(COMITE_ENTREPRISE).categorie(TITULAIRES).tour(1, date)
            .electeursInscritsCount(10)
            .electeursVotantsCount(8)
            .bulletinsBlancsOuNulsCount(2)
            .siegesCount(2)
            .addListe().nom("CFDT").bulletinsValablesCount(5)
                .addCandidat().nom("e1-l1-c1.nom").prenom("e1-l1-c1.prenom").dateNaissance(birthDate1).sexe(HOMME).voixCount(3).endCandidat()
                .addCandidat().nom("e1-l1-c2.nom").prenom("e1-l1-c2.prenom").dateNaissance(birthDate1).sexe(HOMME).voixCount(5).endCandidat()
            .endListe()
            .addListe().nom("CFTC").bulletinsValablesCount(3)
                .addCandidat().nom("e1-l2-c1.nom").prenom("e1-l2-c1.prenom").dateNaissance(birthDate2).sexe(HOMME).voixCount(3).endCandidat()
                .addCandidat().nom("e1-l2-c2.nom").prenom("e1-l2-c2.prenom").dateNaissance(birthDate3).sexe(HOMME).voixCount(2).endCandidat()
            .endListe()
        .endElection()
        .toOperation();
//@formatter:on
        return operation;
    }

    private static final String[] DATE_PATERNS = new String[] {
        "dd/MM/yyyy"
    };

}
