package com.crux.demo.api.listeners;

import com.crux.demo.model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created with IntelliJ IDEA.
 * User: aaron.stone
 * Date: 9/11/13
 * Time: 6:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Bootstrapper implements ServletContextListener {
    private static final Logger log = Logger.getLogger(Bootstrapper.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        try {

            buildMockData();

        } catch(Exception e) {
            log.error("Failed to load mock data.", e);
        }
    }



    /**
     * Build the mock data for the system from JSON.
     *
     * @throws Exception
     */
    private void buildMockData() throws Exception {
        log.info("Building in-memory database...");

        // Build a fake user and add them to the in-memory database of users.
        User fakeUser = new User("Aaron", "Stone", "aaron.stone@effectiveui.com");
        User.addUser(fakeUser);

        fakeUser = new User("Zach", "Hendershot", "zach.hendershot@effectiveui.com");
        User.addUser(fakeUser);

        fakeUser = new User("Nathan", "Ameye", "nathan.ameye@effectiveui.com");
        User.addUser(fakeUser);

        fakeUser = new User("Adam", "Thompson", "adam.thompson@effectiveui.com");
        User.addUser(fakeUser);

        log.info("Successfully loaded mock data.");
    }


    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
