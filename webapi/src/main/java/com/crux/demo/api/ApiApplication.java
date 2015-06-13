package com.crux.demo.api;

import com.crux.demo.api.resources.ApiHostResource;
import com.crux.demo.model.ApiHost;
import io.swagger.jaxrs.config.BeanConfig;
import org.apache.log4j.Logger;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Created by aaronstone on 6/12/15.
 */
@ApplicationPath("/")
public class ApiApplication extends Application {

    private static final Logger log = Logger.getLogger(ApiApplication.class);
    private static final String API_PROPERTIES_FILE = "api.properties";
    private static final String DEFAULT_HOST = "localhost";
    private static final String DEFAULT_PORT = "8080";
    private static final String DEFAULT_API_BASE_PATH = "/api";
    private static final String ENV_API_LOCATION = "API_ADDR";

    private static ApiHost apiHost = ApiHost.getInstance();

    public ApiApplication() {

        // Populate some important values.
        buildApiHostObj();

        // Configure Swagger.
        BeanConfig config = new BeanConfig();
        config.setVersion("1.0.2");
        config.setSchemes(new String[]{"http"});
        config.setHost(apiHost.getHostAddr() + ":" + apiHost.getPort());
        config.setBasePath(apiHost.getApiBasePath());
        config.setResourcePackage("com.crux.demo.api.resources");
        //config.setFilterClass("com.crux.demo.api.filters.ApiAuthorizationFilterImpl");
        config.setScan(true);

    }


    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<Class<?>>();

        // Programmatically add API resources.
        resources.add(com.crux.demo.api.resources.UserResource.class);
        resources.add(ApiHostResource.class);

        // Swagger resources.
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        return resources;
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

}
