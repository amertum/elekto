package elekto.results.dao.xls;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang.time.DateFormatUtils.ISO_DATE_FORMAT;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.fest.util.Arrays;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.collect.Lists;

import elekto.results.model.Candidat;
import elekto.results.model.Categorie;
import elekto.results.model.College.CollegeEnum;
import elekto.results.model.Election;
import elekto.results.model.Liste;
import elekto.results.model.Operation;
import elekto.results.model.Sexe;
import elekto.results.model.Type;

/**
 * Unit test of {@link OperationLoader}.
 */
public class OperationLoaderTest {

    @Test
    public final void testLoadOperationFromFile()
        throws IOException
    {
        final InputStream inputStream = this.getClass().getResourceAsStream("/elections-resultats-modele.xls");
        final Operation operation = new OperationLoader().loadOperation(inputStream);

        final List<Election> elections = newArrayList(operation.getElections());
        assertThat(elections).hasSize(16);

        int candidatGlobalIndex = 0;
        for (final ListIterator<Election> electionIter = elections.listIterator(); electionIter.hasNext();) {
            final int electionIndex = electionIter.nextIndex();
            final Election election = electionIter.next();

            this.logger.info("[" + electionIndex + "] " + election);

            {
                final Type expected = ((electionIndex / 4) % 2 == 0)
                        ? Type.COMITE_ENTREPRISE
                        : Type.DELEGUE_DU_PERSONNEL;
                assertThat(election.getKey().getType()).isEqualTo(expected);
            }
            {
                final Categorie expected = ((electionIndex / 2) % 2 == 0)
                        ? Categorie.TITULAIRES
                        : Categorie.SUPPLEANTS;
                assertThat(election.getKey().getCategorie()).isEqualTo(expected);
            }
            {
                assertThat(election.getKey().getEtablissement().getRaisonSociale()).isEqualTo("LaPoste");
                assertThat(election.getKey().getEtablissement().getAdresse1()).isEqualTo((electionIndex % 2 == 0)
                        ? "5 rue du chat"
                        : "10 rue du chaudron");
                assertThat(election.getKey().getEtablissement().getAdresse2()).isEqualTo((electionIndex % 2 == 0)
                        ? "Près du moulin"
                        : "");
                assertThat(election.getKey().getEtablissement().getCodePostal()).isEqualTo((electionIndex % 2 == 0)
                        ? "44200"
                        : "75001");
                assertThat(election.getKey().getEtablissement().getVille()).isEqualTo((electionIndex % 2 == 0)
                        ? "Nantes"
                        : "Paris");
                assertThat(election.getKey().getEtablissement().getSiret()).isEqualTo((electionIndex % 2 == 0)
                        ? "12345678901234"
                        : "43210987654321");
                assertThat(election.getKey().getEtablissement().getIdcc()).isEqualTo((electionIndex % 2 == 0)
                        ? "4567"
                        : "6543");
            }
            {
                final Object[] expected = ((electionIndex) % 2 == 0)
                        ? Arrays.array(CollegeEnum.OUVRIERS, CollegeEnum.EMPLOYES)
                        : Arrays.array(CollegeEnum.CADRES);
                assertThat(election.getKey().getCollege().getCategories()).containsOnly(expected);
            }

            {
                final int tourIndex = (electionIndex / 8) + 1;
                assertThat(election.getKey().getTour().getIndex()).as("with index=" + electionIndex).isEqualTo(
                        tourIndex);

                if (tourIndex == 1) {
                    assertEquals("2010-12-13", ISO_DATE_FORMAT.format(election.getKey().getTour().getDate()));
                }
                else if (tourIndex == 2) {
                    assertEquals("2010-12-31", ISO_DATE_FORMAT.format(election.getKey().getTour().getDate()));
                }
                else {
                    fail("does not expects tour.index != 1 or != 2, was: " + tourIndex);
                }
            }

            assertEquals("2006-12-13", ISO_DATE_FORMAT.format(election.getScrutinPrecedentDate()));
            assertTrue(((electionIndex / 4) % 2 == 0) == !election.isPartielle());
            assertEquals((electionIndex % 2 == 0)
                    ? 1000
                    : 500, election.getElecteursInscritsCount());
            assertEquals((electionIndex % 2 == 0)
                    ? 800
                    : 400, election.getElecteursVotantsCount());
            assertEquals((electionIndex % 2 == 0)
                    ? 200
                    : 50, election.getBulletinsBlancsOuNulsCount());
            assertEquals(2, election.getSiegesCount());

            final List<Liste> listes = Lists.newArrayList(election.getListes());
            assertThat(listes).hasSize(2);

            for (final ListIterator<Liste> listeIter = listes.listIterator(); listeIter.hasNext();) {
                final int listeIndex = listeIter.nextIndex();
                final Liste liste = listeIter.next();

                assertEquals((listeIndex % 2 == 0)
                        ? "CFDT"
                        : "CFTC", liste.getNom());
                assertEquals((listeIndex % 2 == 0)
                        ? 50
                        : 80, liste.getBulletinsValablesCount());

                final List<Candidat> candidats = Lists.newArrayList(liste.getCandidats());
                assertThat(candidats).hasSize(2);
                for (final ListIterator<Candidat> candidatIter = candidats.listIterator(); candidatIter.hasNext();) {
                    final int candidatIndex = candidatIter.nextIndex();
                    final Candidat candidat = candidatIter.next();

                    final int candidatNomIndex = ((candidatGlobalIndex % 16) + 1);
                    assertEquals("Nom" + candidatNomIndex, candidat.getNom());
                    assertEquals((candidatNomIndex == 2)
                            ? ""
                            : "Prenom" + candidatNomIndex, candidat.getPrenom());
                    if (candidatNomIndex == 2) {
                        assertNull(candidat.getNaissanceDate());
                    }
                    else {
                        assertEquals(
                                "" + (1965 + (candidatGlobalIndex % 16)) + "-08-14",
                                ISO_DATE_FORMAT.format(candidat.getNaissanceDate()));
                    }
                    assertEquals((candidatNomIndex == 2)
                            ? Sexe.NONE
                            : ((listeIndex % 2 == 0)
                                    ? Sexe.HOMME
                                    : Sexe.FEMME), candidat.getSexe());
                    assertEquals(((listeIndex % 2 == 0)
                            ? ((candidatIndex % 2 == 0)
                                    ? 5
                                    : 2)
                            : ((candidatIndex % 2 == 0)
                                    ? 10
                                    : 20)), candidat.getVoixCount());

                    candidatGlobalIndex++;
                }
            }
        }
    }


    @Test
    @Ignore
    public final void testLoadOperationFailsWithWrongSheetName()
        throws IOException
    {
        final InputStream inputStream = this.getClass().getResourceAsStream(
                "/elections-resultats-wrong-sheet-names.xls");
        final Operation operation = new OperationLoader().loadOperation(inputStream);
    }


    @Test
    @Ignore
    public final void testLoadOperationFailsWithWrongSheetHeaders()
        throws IOException
    {
        final InputStream inputStream = this.getClass().getResourceAsStream(
                "/elections-resultats-wrong-sheet-headers.xls");
        final Operation operation = new OperationLoader().loadOperation(inputStream);
    }


    @Test
    @Ignore
    public final void testLoadOperationFailsWithWrongCellValues()
        throws IOException
    {
        final InputStream inputStream = this.getClass().getResourceAsStream(
                "/elections-resultats-wrong-sheet-values.xls");
        final Operation operation = new OperationLoader().loadOperation(inputStream);
    }


    @BeforeClass
    public static void initToStringStyle()
    {
        ToStringBuilder.setDefaultStyle(ToStringStyle.SIMPLE_STYLE);
    }

    private final Logger logger = Logger.getLogger(this.getClass());

}
