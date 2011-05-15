package controllers;

import java.util.List;

import models.Election;
import play.mvc.Before;
import play.mvc.Controller;

public class Elector
        extends Controller {

    @Before
    static void checkClientId(
            final String clientId)
    {
        notFoundIfNull(clientId, "clientId");
    }


    @Before(unless = {
            "login", "doLogin"
    })
    static void checkAuthentication(
            final String clientId)
    {
        if (session.get("elector") == null) {
            login(clientId);
        }
    }


    public static void index(
            final String clientId)
    {
        System.out.println("index/clientId:" + clientId);
        elections(clientId);
    }


    public static void login(
            final String clientId)
    {
        System.out.println("login/clientId:" + clientId);
        render();
    }


    public static void doLogin(
            final String clientId,
            final String username,
            final String password)
    {
        System.out.println("doLogin/clientId:" + clientId);

        System.out.println(username + ":" + password);
        session.put("elector", session.getId());

        elections(clientId);
    }


    public static void doLogout(
            final String clientId)
    {
        System.out.println("doLogout/clientId:" + clientId);
        session.clear();

        login(clientId);
    }


    public static void elections(
            final String clientId)
    {
        System.out.println("elections/clientId:" + clientId);
        final List<Election> elections = Election.findAll();

        render(elections);
    }


    public static void doVote(
            final String clientId)
    {
        System.out.println("dovote/clientId:" + clientId);
        elections(clientId);
    }

}
