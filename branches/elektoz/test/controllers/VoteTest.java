package controllers;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import play.mvc.Http;
import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class VoteTest
        extends FunctionalTest {

    @Test
    public void testElectionsJson()
    {
        {
            final Response response = GET("/acme/elector/login");
            assertStatus(200, response);
        }
        {
            final Map<String, String> params = new HashMap<String, String>();
            params.put("username", "bob");
            params.put("password", "dylan");
            final Response response = followRedirect(POST("/acme/elector/login", params, new HashMap<String, File>()));
            assertStatus(200, response);
        }
        {
            final Response response = followRedirect(GET("/acme/elector/elections.json"));
            assertStatus(200, response);
            assertContentEquals(
                    "[{\"name\":\"election1\",\"id\":1},{\"name\":\"election2\",\"id\":2},{\"name\":\"election3\",\"id\":3},{\"name\":\"election4\",\"id\":4}]",
                    response);
        }
    }


    @Test
    @Ignore
    public void testRootPage()
    {
        final Response response = GET("/");
        assertStatus(404, response);
    }


    private static Response followRedirect(
            final Response response)
    {
        if (Http.StatusCode.FOUND == response.status) {
            final Http.Header redirectedTo = response.headers.get("Location");
            java.net.URL redirectedUrl = null;
            try {
                redirectedUrl = new java.net.URL(redirectedTo.value());
            }
            catch (final MalformedURLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("followRedirect: " + redirectedUrl.getPath());

            return followRedirect(GET(redirectedUrl.getPath()));
        }
        return response;
    }

}
