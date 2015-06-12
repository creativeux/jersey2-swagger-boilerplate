package com.crux.demo.api;

import io.swagger.jaxrs.config.BeanConfig;
import org.apache.log4j.Logger;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by aaronstone on 6/12/15.
 */
@ApplicationPath("/")
public class SwaggerApplication extends Application {

    private static final Logger log = Logger.getLogger(SwaggerApplication.class);

    public SwaggerApplication() {
        BeanConfig config = new BeanConfig();
        config.setVersion("1.0.2");
        config.setSchemes(new String[]{"http"});
        config.setHost("localhost:8080");
        config.setBasePath("/api");
        config.setResourcePackage("com.crux.demo.api.resources");
        //config.setFilterClass("com.crux.demo.api.filters.ApiAuthorizationFilterImpl");
        config.setScan(true);
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<Class<?>>();

        // Programmatically add API resources.
        resources.add(com.crux.demo.api.resources.UserResource.class);

        // Swagger resources.
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        return resources;
    }
}
