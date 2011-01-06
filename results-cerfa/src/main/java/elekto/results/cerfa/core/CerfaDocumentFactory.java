package elekto.results.cerfa.core;

import java.io.OutputStream;

import elekto.results.model.Operation;

public interface CerfaDocumentFactory {

    void create(
            Operation operation,
            OutputStream outputStream);

}
