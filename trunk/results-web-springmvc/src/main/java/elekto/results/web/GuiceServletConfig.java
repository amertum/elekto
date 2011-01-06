package elekto.results.web;

import org.springframework.web.servlet.DispatcherServlet;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class GuiceServletConfig
        extends GuiceServletContextListener {

    @Override
    protected Injector getInjector()
    {
        return Guice.createInjector(new ServletModule() {

            @Override
            protected void configureServlets()
            {
                this.bind(DispatcherServlet.class).in(Singleton.class);
                this.serve("/*").with(DispatcherServlet.class);
            }
        });
    }

}
