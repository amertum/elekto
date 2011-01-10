package elekto.results.cerfa;

import org.junit.Test;

import elekto.results.cerfa.core.CerfaDocumentFactory;

/**
 * Unit test of {@link CerfaDocumentFactory}.
 */
public class CerfaDocumentFactoryTest {

    @Test
    public final void testCreate()
    {
        final CerfaDocumentFactory factory = new CerfaDocumentFactory();
        factory.create(null);
    }

}
