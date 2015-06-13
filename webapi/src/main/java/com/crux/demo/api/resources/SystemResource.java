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
     * Get all IPs for the system.
     *
     * @return
     * @throws Exception
     */
    @GET
    @Path("/ip")
    @ApiOperation(
            value = "Get IP addresses for all NICs",
            notes = "Loops over all NICs and returns the IP address associated with each one.",
            response = List.class)
    public Response getIpAddresses() throws Exception {

        log.info("Getting all IP addresses.");

        List<String> ips = new ArrayList<String>();

        Enumeration e = NetworkInterface.getNetworkInterfaces();
        while(e.hasMoreElements())
        {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements())
            {
                InetAddress i = (InetAddress) ee.nextElement();

                if(i instanceof Inet4Address) {
                    ips.add(i.getHostAddress());
                }
            }
        }

        return Response.ok().entity(ips).build();
    }



}
