package com.muranyibence.jpa.main;

import com.muranyibence.jpa.application.Application;
import com.muranyibence.jpa.exception.RepositoryException;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

/**
 *
 * @author Bence
 */
public class Main {

    private Main() {

    }

    public static void main(String[] args) throws RepositoryException {
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        Application application = container.instance().select(Application.class).get();
        application.execute();
        weld.shutdown();
    }

}
