package controllers;

import java.util.List;

import models.Election;
import play.mvc.Before;
import play.mvc.Controller;

public class Vote
        extends Controller {

    @Before
    static void checkClientAndContext(
            final String client,
            final String context)
    {
        notFoundIfNull(client, "client");
        notFoundIfNull(context, "context");
    }


    @Before(unless = {
            "login", "signin", "logout"
    })
    static void checkAuthentication(
            final String client,
            final String context)
    {
        if (session.get("elector") == null) {
            if (request.format == "html") {
                login(client, context);
            }
            else {
                forbidden();
            }
        }
    }


    public static void context(
            final String client,
            final String context)
    {
        render();
    }


    public static void login(
            final String client,
            final String context)
    {
        render();
    }


    public static void signin(
            final String client,
            final String context,
            final String username,
            final String password)
    {
        System.out.println("signin: " + username + "/" + password);
        session.put("elector", session.getId());

        context(client, context);
    }


    public static void logout(
            final String client,
            final String context)
    {
        session.clear();

        login(client, context);
    }


    public static void elections(
            final String client,
            final String context)
    {
        final List<Election> elections = Election.findAll();

        renderJSON(elections);
    }


    public static void cast(
            final String client,
            final String context)
    {
        registration(client, context);
    }


    public static void registration(
            final String client,
            final String context)
    {
        renderJSON(null);
    }

}
