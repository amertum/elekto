package elekto.results.cerfa;

import static elekto.results.cerfa.raw.CerfaResource.CERFA_COMITE_ENTREPRISE_MEMBRES_TITULAIRES;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.junit.Test;

import elekto.results.cerfa.core.CompleteCerfaDocument;
import elekto.results.cerfa.model.ElectionModel;

public class CompleteCerfaDocumentTest {

    @Test
    public void testMakeHugeMultiPagesDocument()
        throws IOException
    {
    }


    /**
     * <pre>
     * Operation
     *      Round
     *          Pool : provides elected candidat rules, ie: two exclusive ordered elected position [titulaire, suppleant]
     *              Election
     *                  Etablissement
     *                  College
     *                  Liste
     *                      Candidat
     * </pre>
     * 
     * <pre>
     * Election
     *      Round
     *          Pool : provides elected candidat rules, ie: two exclusive ordered elected position [titulaire, suppleant]
     *              Poll
     *                  Etablissement
     *                  College
     *                  Liste
     *                      Candidat
     * </pre>
     */
    @Test
    public void testMakeMultiPagesDocument()
        throws IOException
    {
        final ElectionModel electionResults = mock(ElectionModel.class);
        //        Mockito.when(electionResults.getType());
        //        Mockito.when(electionResults.getPoll().getMembers());
        //        Mockito.when(electionResults.getPoll().getContact());
        //        Mockito.when(electionResults.getListesCommunes());
        //        Mockito.when(electionResults.getEtablissement());
        //        Mockito.when(electionResults.getCollege());
        //        Mockito.when(electionResults.getListes());
        //
        //        Mockito.when(electionResults.getRoundCount());
        //        Mockito.when(electionResults.getCurrentRound().getIndex());
        //        Mockito.when(electionResults.getRound(0).getElectionsResults());
        //        Mockito.when(electionResults.getSameElectionResultsOnRound(0).getElecteursInscritsCount());
        //
        //        Mockito.when(electionResults.getElecteursInscritsCount());
        //        Mockito.when(electionResults.getQuorum());

        final CompleteCerfaDocument document = new CompleteCerfaDocument(
                CERFA_COMITE_ENTREPRISE_MEMBRES_TITULAIRES,
                electionResults);

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
        assertThat(pdDocument.getNumberOfPages()).isEqualTo(4);
        final PDPage page1 = (PDPage) pdDocument.getDocumentCatalog().getAllPages().get(0);
        assertThat(page1).isNotNull();
        final PDPage page2 = (PDPage) pdDocument.getDocumentCatalog().getAllPages().get(1);
        assertThat(page2).isNotNull();
        pdDocument.close();

        // TODO more asserts

    }


    @Test
    public void testMakeMultiPagesDocumentWithListeSpaningAcrossMultiPages()
        throws IOException
    {
    }

}
