package controllers;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class ElectorTest
        extends FunctionalTest {

    @Test
    public void testHomePage()
    {
        //this.clearCookies();
        assertStatus(200, GET("/acme/elector/login"));

        {
            final Map<String, String> params = new HashMap<String, String>();
            params.put("username", "bob");
            params.put("password", "dylan");
            final Response response = POST("/acme/elector/login", params);
            assertStatus(302, response);
        }

        {
            final Response response = GET("/acme/elector/elections");
            assertStatus(200, response);
            //            assertContentEquals(
            //                    "[{\"name\":\"election1\",\"id\":1},{\"name\":\"election2\",\"id\":2},{\"name\":\"election3\",\"id\":3},{\"name\":\"election4\",\"id\":4}]",
            //                    response);
        }
    }


    @Test
    @Ignore
    public void testRootPage()
    {
        final Response response = GET("/");
        assertStatus(404, response);
    }

}
