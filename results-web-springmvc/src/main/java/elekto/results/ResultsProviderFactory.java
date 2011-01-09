package elekto.results;

import java.io.InputStream;

import com.google.common.io.InputSupplier;

import elekto.results.cerfa.core.CerfaDocumentFactory;
import elekto.results.dao.xls.OperationLoader;

/**
 * 
 */
public class ResultsProviderFactory {
    
    public ResultsProviderFactory(
            final OperationLoader operationLoader,
            final CerfaDocumentFactory cerfaDocumentFactory)
    {
        this.operationLoader = operationLoader;
        this.cerfaDocumentFactory = cerfaDocumentFactory;
    }
    

    public ResultsProvider create(
            final InputStream inputStream)
    {
        return new ResultsProvider(this.operationLoader, this.cerfaDocumentFactory, inputStream);
    }
    

    private final OperationLoader operationLoader;
    
    private final CerfaDocumentFactory cerfaDocumentFactory;
    
}
