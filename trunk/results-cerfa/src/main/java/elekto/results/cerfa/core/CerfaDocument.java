package elekto.results.cerfa.core;

import java.io.IOException;
import java.io.OutputStream;

public interface CerfaDocument {

    void save(
            OutputStream outStream)
        throws IOException;

}
