package elekto.results;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import elekto.results.cerfa.core.CerfaDocument;
import elekto.results.cerfa.core.CerfaDocumentFactory;
import elekto.results.dao.xls.OperationLoader;
import elekto.results.model.Operation;

/**
 * 
 */
public class ResultsProvider {

    public ResultsProvider(
            final OperationLoader operationLoader,
            final CerfaDocumentFactory cerfaDocumentFactory,
            final InputStream inputStream)
    {
        this.operationLoader = operationLoader;
        this.cerfaDocumentFactory = cerfaDocumentFactory;
        this.inputStream = inputStream;
    }


    public HSSFWorkbook getInputResults()
        throws IOException
    {
        if (this.workbook == null) {
            this.workbook = new HSSFWorkbook(this.inputStream);
        }
        return this.workbook;
    }


    public Operation getOperation()
        throws IOException
    {
        return this.operationLoader.loadOperation(this.getInputResults());
    }


    public CerfaDocument getCerfaDocument()
        throws IOException
    {
        return this.cerfaDocumentFactory.create(this.getOperation());
    }

    private final OperationLoader operationLoader;

    private final CerfaDocumentFactory cerfaDocumentFactory;

    private final InputStream inputStream;

    private HSSFWorkbook workbook;

}
