package elekto.results.dao.xls;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Unit test of {@link Templates}.
 */
public class TemplatesTest {

    @Test
    public final void testGetElectionsTemplateAsStream()
    {
        assertNotNull(Templates.getElectionsTemplateAsStream());
    }

}
