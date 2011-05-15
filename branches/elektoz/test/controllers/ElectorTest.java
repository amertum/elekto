package controllers;

import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class ElectorTest
        extends FunctionalTest {

    @Test
    public void testHomePage()
    {
        final Response response = GET("/acme");
        assertStatus(200, response);
    }


    @Test
    public void testRootPage()
    {
        final Response response = GET("/");
        assertStatus(404, response);
    }

}
