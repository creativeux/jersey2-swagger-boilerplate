package com.crux.demo.api.resources;

import com.crux.demo.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by aaronstone on 6/12/15.
 */

@Path("/system")
@Api(value = "/system", description = "System operations")
@Produces(MediaType.APPLICATION_JSON)
public class SystemResource {

    private static final Logger log = Logger.getLogger(SystemResource.class);

    /**
     * Get IP for the API.
     *
     * @return
     * @throws Exception
     */
    @GET
    @Path("/ip")
    @ApiOperation(
            value = "Get IP address for API",
            notes = "Yup.",
            response = String.class)
    public Response getIpAddress() throws Exception {

        log.info("Getting API address.");

        return Response.ok().entity(System.getenv("API_ADDR")).build();
    }



}
