package com.crux.demo.api.resources;

import com.crux.demo.model.ApiHost;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by aaronstone on 6/12/15.
 */

@Path("/host")
@Api(value = "/host", description = "API host operations")
@Produces(MediaType.APPLICATION_JSON)
public class ApiHostResource {

    private static final Logger log = Logger.getLogger(ApiHostResource.class);

    /**
     * Get IP for the API.
     *
     * @return
     * @throws Exception
     */
    @GET
    @ApiOperation(
            value = "API server host details",
            notes = "Get the API server host address, port, and API root.",
            response = ApiHost.class)
    public Response getApiHostAddress() throws Exception {

        log.info("Getting API address.");

        return Response.ok().entity(ApiHost.getInstance()).build();
    }



}
