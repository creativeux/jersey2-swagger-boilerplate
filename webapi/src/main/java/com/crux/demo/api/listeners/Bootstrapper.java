package com.crux.demo.api.listeners;

import com.crux.demo.model.ApiHost;
import com.crux.demo.model.User;
import io.swagger.models.Contact;
import io.swagger.models.Info;
import io.swagger.models.License;
import io.swagger.models.Swagger;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: aaron.stone
 * Date: 9/11/13
 * Time: 6:35 PM
 * To change this template use File | Settings | File Templates.
 */
@WebListener
public class Bootstrapper implements ServletContextListener {
    private static final Logger log = Logger.getLogger(Bootstrapper.class);

    private static final String API_PROPERTIES_FILE = "api.properties";
    private static final String DEFAULT_HOST = "localhost";
    private static final String DEFAULT_PORT = "8080";
    private static final String DEFAULT_API_BASE_PATH = "/api";
    private static final String ENV_API_LOCATION = "API_ADDR";

    private static ApiHost apiHost = ApiHost.getInstance();

    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext context = servletContextEvent.getServletContext();


        try {

            buildApiHostObj();

            buildMockData();

            buildSwaggerInfo(servletContextEvent.getServletContext());

        } catch(Exception e) {
            log.error("Failed to load mock data.", e);
        }
    }



    /**
     * Build the mock data for the system from JSON.
     *
     * @throws Exception
     */
    private static void buildMockData() throws Exception {
        log.info("Building in-memory database...");

        // Build a fake user and add them to the in-memory database of users.
        User fakeUser = new User("Aaron", "Stone", "aaron.stone@effectiveui.com", "EffectiveUI", "(303) 555-1234", "2162 Market St", "Denver, CO 80203");
        User.addUser(fakeUser);

        fakeUser = new User("Zach", "Hendershot", "zach.hendershot@effectiveui.com", "EffectiveUI", "(303) 555-1234", "2162 Market St", "Denver, CO 80203");
        User.addUser(fakeUser);

        fakeUser = new User("Nathan", "Ameye", "nathan.ameye@effectiveui.com", "EffectiveUI", "(303) 555-1234", "2162 Market St", "Denver, CO 80203");
        User.addUser(fakeUser);

        log.info("Successfully loaded mock data.");
    }


    private static void buildSwaggerInfo(ServletContext context) throws Exception {

        // TODO: Update Swagger details from demo.
        Info info = new Info()
                .title("Sample Swagger/Jersey2 App")
                .description("Boilerplate Jersey 2 Swagger documentation.")
                //.termsOfService("http://helloreverb.com/terms/")
                .contact(new Contact()
                        .email("aaronastone@gmail.com"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://www.apache.org/licenses/LICENSE-2.0.html"));

        Swagger swagger = new Swagger().info(info);

        // TODO: Adjust the security for this.
//        swagger.securityDefinition("api_key", new ApiKeyAuthDefinition("api_key", In.HEADER));
//        swagger.securityDefinition("petstore_auth",
//                new OAuth2Definition()
//                        .implicit("http://petstore.swagger.io/api/oauth/dialog")
//                        .scope("read:pets", "read your pets")
//                        .scope("write:pets", "modify pets in your account"));
        context.setAttribute("swagger", swagger);
    }

    private static void buildApiHostObj() {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = Thread.currentThread().getContextClassLoader().getResourceAsStream(API_PROPERTIES_FILE);

            if(input==null){
                log.error("Unable to find API properties file.");
                return;
            }

            prop.load(input);

            // Check for the environment variable override first.
            String host = System.getenv(ENV_API_LOCATION);

            // If no variable exists, get from properties.  If nothing there, default to localhost.
            if(host == null || host.length() == 0) {
                host = prop.getProperty("host", DEFAULT_HOST);

                if(host.length() == 0) {
                    host = DEFAULT_HOST;
                }
            }

            int port = Integer.valueOf(prop.getProperty("port", DEFAULT_PORT));
            String apiBasePath = prop.getProperty("basePath", DEFAULT_API_BASE_PATH);

            // Update the ApiHost object
            apiHost.setHostAddr(host);
            apiHost.setPort(port);
            apiHost.setApiBasePath(apiBasePath);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        log.info("API location: " + apiHost.getHostAddr() + ":" + apiHost.getPort() + apiHost.getApiBasePath());
    }


    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
