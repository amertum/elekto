package elekto.results.cerfa;

import static elekto.results.cerfa.raw.CerfaResource.CERFA_COMITE_ENTREPRISE_MEMBRES_TITULAIRES;
import static elekto.results.cerfa.raw.RawCerfaDocument.makeCerfa;
import static java.util.Collections.nCopies;
import static org.apache.commons.lang.StringUtils.repeat;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.junit.Test;

import elekto.results.cerfa.model.CandidatInfos;
import elekto.results.cerfa.model.ListeCommuneInfos;
import elekto.results.cerfa.model.MembreBureauVoteInfos;
import elekto.results.cerfa.model.Sexe;
import elekto.results.cerfa.raw.RawCerfaDocument;

/**
 * Unit test of {@link RawCerfaDocument}.
 */
public class RawCerfaDocumentTest {

    @Test
    public void testDrawAllAndSave()
        throws Exception
    {
        final RawCerfaDocument document = makeCerfa(CERFA_COMITE_ENTREPRISE_MEMBRES_TITULAIRES);
        initElectionModel(document);

        // set a different system locale to verify that number and date format is set to FRANCE, not to default 
        Locale.setDefault(Locale.US);

        // save

        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        document.save(outputStream);

        final File pdfFile = new File("target/tmp/" + this.getClass().getSimpleName() + "_"
                + Thread.currentThread().getStackTrace()[1].getMethodName() + ".pdf");
        pdfFile.getParentFile().mkdirs();
        final FileOutputStream fileOutputStream = new FileOutputStream(pdfFile);
        IOUtils.copy(new ByteArrayInputStream(outputStream.toByteArray()), fileOutputStream);
        IOUtils.closeQuietly(fileOutputStream);

        // test

        final PDDocument pdDocument = PDDocument.load(new ByteArrayInputStream(outputStream.toByteArray()));
        assertThat(pdDocument.getNumberOfPages()).isEqualTo(2);
        final PDPage page1 = (PDPage) pdDocument.getDocumentCatalog().getAllPages().get(0);
        assertThat(page1).isNotNull();
        final PDPage page2 = (PDPage) pdDocument.getDocumentCatalog().getAllPages().get(1);
        assertThat(page2).isNotNull();
        pdDocument.close();

        // TODO more asserts
    }


    @Test
    public void testEmptyAndSave()
        throws Exception
    {
        final RawCerfaDocument document = makeCerfa(CERFA_COMITE_ENTREPRISE_MEMBRES_TITULAIRES);

        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        document.save(outputStream);

        final File pdfFile = new File("target/tmp/" + this.getClass().getSimpleName() + "_"
                + Thread.currentThread().getStackTrace()[1].getMethodName() + ".pdf");
        pdfFile.getParentFile().mkdirs();
        final FileOutputStream fileOutputStream = new FileOutputStream(pdfFile);
        IOUtils.copy(new ByteArrayInputStream(outputStream.toByteArray()), fileOutputStream);
        IOUtils.closeQuietly(fileOutputStream);
    }


    @Test
    public void testSaveTwiceIsAnError()
        throws Exception
    {
        final RawCerfaDocument document = makeCerfa(CERFA_COMITE_ENTREPRISE_MEMBRES_TITULAIRES);

        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        document.save(outputStream);
        try {
            document.save(outputStream);

            fail("Expects " + UnsupportedOperationException.class);
        }
        catch (final UnsupportedOperationException e) {
            assertThat(e).hasMessage("document cannot be saved twice, use a new instance.");
        }
    }


    static void initElectionModel(
            final RawCerfaDocument document)
    {
        final String more = repeat("i", 100);

        document.getEtablissement().setRaisonSociale("Entreprise Exemple X" + more);
        document.getEtablissement().setAdresse("1 rue truc" + more, "petite porte" + more);
        document.getEtablissement().setCodePostal("75001" + more);
        document.getEtablissement().setVille("Paris paton" + more);
        document.getEtablissement().setSiret("12312312312345" + more);
        document.getEtablissement().setAutresSiret(nCopies(11, "12312312312345" + more));
        document.getEtablissement().setIdcc("12345" + more);

        document.getElection().setMandatDureeAnnee(44);
        document.getElection().setCollegesNombre(2111);
        document.getElection().setPartielle(true);
        document.getElection().setScrutinPrecedentDate(new Date());

        document.getCollege().setDenominationUnique(true);
        document.getCollege().setDenominationPremier(true);
        document.getCollege().setDenominationDeuxieme(true);
        document.getCollege().setDenominationTroisieme(true);
        document.getCollege().setDenominationAutre(true);

        document.getCollege().setCompositionOuvriers(true);
        document.getCollege().setCompositionEmployes(true);
        document.getCollege().setCompositionTechniciens(true);
        document.getCollege().setCompositionAgentsDeMaitrise(true);
        document.getCollege().setCompositionIngenieurs(true);
        document.getCollege().setCompositionCadres(true);
        document.getCollege().setCollegeCompositionAutres(true, "Dirigeants" + more, "éèçàù" + more, "Artistes" + more);

        // 1er tour

        document.getPremierTourResultats().setDate(new Date());
        document.getPremierTourResultats().setElecteursInscritsNombre(10000);
        document.getPremierTourResultats().setVotantsNombre(9999);
        document.getPremierTourResultats().setBulletinsBlancsOuNulsNombre(1001111);
        document.getPremierTourResultats().setBulletinsValablesNombre(10);

        document.getPremierTourResultats().setCarenceOui(true);
        document.getPremierTourResultats().setCarenceNon(true);
        document.getPremierTourResultats().setQuorum(120.511f);
        document.getPremierTourResultats().setQuorumAtteintOui(true);
        document.getPremierTourResultats().setQuorumAtteintNon(true);

        document.getPremierTourResultats().setListesPresenteesNombre(431);
        document.getPremierTourResultats().setSiegesPourvoirNombre(511);
        document.getPremierTourResultats().setQuotientElectoralNumerateur(522.506f);
        document.getPremierTourResultats().setQuotientElectoralDenominateur(145);
        document.getPremierTourResultats().setQuotientElectoral(211.506f); // should display 211.51

        for (int i = 0; i < 21; i++) {
            // TODO ? final CandidatInfos candidatInfos = document.getPremierTourCandidats().add();
            final CandidatInfos candidatInfos = new CandidatInfos();

            candidatInfos.setNom("Palaiseaux");
            candidatInfos.setPrenom("Jean-Sébastien");
            candidatInfos.setSexe(Sexe.FEMME);
            candidatInfos.setSyndicat("CGT-CFDT");

            candidatInfos.setListeBulletinsValablesNombre(1111);
            candidatInfos.setCandidatVoixNombre(5555);
            candidatInfos.setListeCandidatsVoixTotal(77777);
            candidatInfos.setListeCandidatsNombre(10);

            candidatInfos.setListeVoixMoyenne(11.05f);
            candidatInfos.setListeSiegesAttribuesQuotient(3.05f);
            candidatInfos.setListeSiegesAttribuesPlusForteMoyenneSiege1(3.05f);
            candidatInfos.setListeSiegesAttribuesPlusForteMoyenneSiege2(2.05f);
            candidatInfos.setListeSiegesAttribuesPlusForteMoyenneSiege3(1.05f);

            candidatInfos.setElu(true);
            candidatInfos.setListeElusNombre(5);

            document.addPremierTourCandidat(candidatInfos);
        }

        for (int i = 0; i < 8; i++) {
            // TODO ? final ListeCommuneInfos listeCommune = document.getListesCommunes().add();
            final ListeCommuneInfos listeCommune = new ListeCommuneInfos();
            listeCommune.setNom("CGT-CFDT");
            listeCommune.setOrganisations("CGT", "CFDT");
            listeCommune.setSuffragesRepartitionPourcentage(0.25f, 0.78f);

            document.addListeCommune(listeCommune);
        }

        // 2nd tour

        document.getSecondTourResultats().setDate(new Date());
        document.getSecondTourResultats().setElecteursInscritsNombre(10000);
        document.getSecondTourResultats().setVotantsNombre(9999);
        document.getSecondTourResultats().setBulletinsBlancsOuNulsNombre(1001111);
        document.getSecondTourResultats().setBulletinsValablesNombre(10);

        document.getSecondTourResultats().setCarenceOui(true);
        document.getSecondTourResultats().setCarenceNon(true);

        document.getSecondTourResultats().setListesPresenteesNombre(431);
        document.getSecondTourResultats().setSiegesPourvoirNombre(511);
        document.getSecondTourResultats().setQuotientElectoralNumerateur(522.506f);
        document.getSecondTourResultats().setQuotientElectoralDenominateur(145);
        document.getSecondTourResultats().setQuotientElectoral(211.506f); // should display 211.51

        for (int i = 0; i < 21; i++) {
            final CandidatInfos candidatInfos = new CandidatInfos();

            candidatInfos.setNom("Palaiseaux");
            candidatInfos.setPrenom("Jean-Sébastien");
            candidatInfos.setSexe(Sexe.HOMME);
            candidatInfos.setSyndicat("CGT-CFDT");

            candidatInfos.setListeBulletinsValablesNombre(1111);
            candidatInfos.setCandidatVoixNombre(5555);
            candidatInfos.setListeCandidatsVoixTotal(77777);
            candidatInfos.setListeCandidatsNombre(10);

            candidatInfos.setListeVoixMoyenne(11.05f);
            candidatInfos.setListeSiegesAttribuesQuotient(3.05f);
            candidatInfos.setListeSiegesAttribuesPlusForteMoyenneSiege1(3.05f);
            candidatInfos.setListeSiegesAttribuesPlusForteMoyenneSiege2(2.05f);
            candidatInfos.setListeSiegesAttribuesPlusForteMoyenneSiege3(1.05f);

            candidatInfos.setElu(true);
            candidatInfos.setListeElusNombre(5);

            document.addSecondTourCandidat(candidatInfos);
        }

        for (int i = 0; i < 8; i++) {
            final MembreBureauVoteInfos membreBureauVote = new MembreBureauVoteInfos();
            membreBureauVote.setNom("Palaiseaux");
            membreBureauVote.setPrenom("Jean-Sébastien" + more);
            membreBureauVote.setOrganisation("CGT" + more);

            document.addMembreBureauVote(membreBureauVote);
        }

        document.getContactEntreprise().setNom("Palaiseaux" + more);
        document.getContactEntreprise().setPrenom("Jean-Sébastien" + more);
        document.getContactEntreprise().setFonction("Cadre" + more);
        document.getContactEntreprise().setTelephone("01 23 45 67 89" + more);
        document.getContactEntreprise().setTelecopie("01 23 45 67 89" + more);
        document.getContactEntreprise().setEmail("jean-sebastien.palaiseaux@masuperentreprise.fr" + more);

        document.getPageNumeros().setNumeroPremierePage(3);
        document.getPageNumeros().setNombreTotalPages(6);
    }

}
