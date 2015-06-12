package com.crux.demo.api;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Created by aaronstone on 6/12/15.
 */
@ApplicationPath("/")
public class ApiApplication extends ResourceConfig {

    private static final Logger log = Logger.getLogger(ApiApplication.class);

    public ApiApplication() {
        packages("com.wordnik.swagger.jaxrs.listing;com.crux.demo.api.resources");
    }
}
