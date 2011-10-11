package elekto.results.cerfa;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import elekto.results.cerfa.core.CerfaDocumentFactory;

/**
 * Unit test of {@link CerfaDocumentFactory}.
 */
public class CerfaDocumentFactoryTest {

    @Test
    @Ignore("not really necessary due to ResultsProviderIT")
    public final void testCreate()
        throws IOException
    {
        final CerfaDocumentFactory factory = new CerfaDocumentFactory();
        factory.create(null);
    }

}
