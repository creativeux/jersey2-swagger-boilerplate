package com.crux.demo.api;

import com.crux.demo.api.resources.UserResource;
import com.crux.demo.model.ApiHost;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.apache.log4j.Logger;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by aaronstone on 6/12/15.
 */
@ApplicationPath("/")
public class ApiApplication extends Application {

    private static final Logger log = Logger.getLogger(ApiApplication.class);

    public ApiApplication() {

        ApiHost apiHost = ApiHost.getInstance();

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

        resources.add(UserResource.class);

        // Swagger resources.
        resources.add(ApiListingResource.class);
        resources.add(SwaggerSerializers.class);

        return resources;
    }


}
