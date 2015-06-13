package com.crux.demo.api;

import io.swagger.jaxrs.config.BeanConfig;
import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.Enumeration;
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
    private static final String DEFAULT_PORT = "8080";

    private static String host;
    private static int port;
    private static String apiBasePath;


    public ApiApplication() {

        // Populate some important values.
        getApiProps();

        // Create the API address.
        InetSocketAddress addr = InetSocketAddress.createUnresolved(getIpAddress().getHostAddress(), port);
        log.info("API location: " + addr.getHostName() + ":" + addr.getPort() + apiBasePath);

        // Configure Swagger.
        BeanConfig config = new BeanConfig();
        config.setVersion("1.0.2");
        config.setSchemes(new String[]{"http"});
        config.setHost("localhost:" + addr.getPort());
        config.setBasePath(apiBasePath);
        config.setResourcePackage("com.crux.demo.api.resources");
        //config.setFilterClass("com.crux.demo.api.filters.ApiAuthorizationFilterImpl");
        config.setScan(true);

    }


    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<Class<?>>();

        // Programmatically add API resources.
        resources.add(com.crux.demo.api.resources.UserResource.class);
        resources.add(com.crux.demo.api.resources.SystemResource.class);

        // Swagger resources.
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        return resources;
    }




    private static void getApiProps() {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = Thread.currentThread().getContextClassLoader().getResourceAsStream(API_PROPERTIES_FILE);

            if(input==null){
                log.error("Unable to find API properties file.");
                return;
            }

            prop.load(input);

            host = prop.getProperty("host");

            if(host != null && host.length() == 0) {
                host = InetAddress.getLocalHost().getHostAddress();
            }

            port = Integer.valueOf(prop.getProperty("port", DEFAULT_PORT));
            apiBasePath = prop.getProperty("basePath", "/api");

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
    }


    private static Inet4Address getIpAddress() {
        try {
            Enumeration e = NetworkInterface.getNetworkInterfaces();
            while(e.hasMoreElements())
            {
                NetworkInterface n = (NetworkInterface) e.nextElement();
                Enumeration ee = n.getInetAddresses();
                while (ee.hasMoreElements())
                {
                    InetAddress i = (InetAddress) ee.nextElement();

                    if(i instanceof Inet4Address && !i.getHostAddress().startsWith("127.") && !i.getHostAddress().startsWith("10.")) {
                        return (Inet4Address)i;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        return null;

    }
}
