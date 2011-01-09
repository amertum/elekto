package elekto.results;

import static com.google.common.io.Resources.getResource;
import static com.google.common.io.Resources.newInputStreamSupplier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.junit.Test;

import com.google.common.io.InputSupplier;

import elekto.results.cerfa.core.CerfaDocument;
import elekto.results.cerfa.core.CerfaDocumentFactory;
import elekto.results.dao.xls.OperationLoader;

/**
 * Integration test of {@link ResultsProvider}.
 */
public class ResultsProviderIT {

    @Test
    public final void testGetCerfaDocument()
        throws Exception
    {
        final ResultsProviderFactory factory = new ResultsProviderFactory(
                new OperationLoader(),
                new CerfaDocumentFactory());
        final InputSupplier<InputStream> resultsSupplier = newInputStreamSupplier(getResource("results-template-example.xls"));
        final ResultsProvider resultsProvider = factory.create(resultsSupplier.getInput());

        resultsProvider.getInputResults();
        resultsProvider.getOperation();
        final CerfaDocument cerfaDocument = resultsProvider.getCerfaDocument();

        final File tmpFile = new File("target/cerfa.pdf");
        cerfaDocument.save(new FileOutputStream(tmpFile));
    }

}
