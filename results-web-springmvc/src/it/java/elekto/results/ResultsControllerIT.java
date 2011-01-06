package elekto.results;

import static com.mycila.testing.plugins.jetty.JettyRunWarHelper.getWebappUrl;
import static elekto.results.ResultsController.FORM_ATTRIBUTE_ELECTIONS_MODEL_FILE;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.mycila.testing.junit.MycilaJunitRunner;
import com.mycila.testing.plugins.jetty.JettyRunWar;

/**
 * Integration test of {@link ResultsController}
 */
@RunWith(MycilaJunitRunner.class)
@JettyRunWar
public class ResultsControllerIT {

    @Test
    public void testCalculateEmpty()
        throws Exception
    {
        final WebDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_3);
        driver.get(getWebappUrl(this.getClass()) + "/results/calculate");

        assertThat(driver.getTitle()).isEqualTo("Elekto - Elections CE & DP - Calcul des résultats");

        final WebElement fileInput = driver.findElement(By.name(FORM_ATTRIBUTE_ELECTIONS_MODEL_FILE));
        final String filepath = new File(this.getClass().getResource("/results-template-example.xls").toURI()).getAbsolutePath();
        fileInput.sendKeys(filepath);
        fileInput.submit();

        assertEquals("currentTourIndex : 1", driver.findElement(By.id("currentTourIndex")).getText());

        // TODO assert XLS and PDF results links

        driver.quit();
    }
}
