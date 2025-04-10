package org.middle.earth;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.middle.earth.service.HobbitService;

@Path("/api/hobbit/unsecured")
public class HobbitUnsecuredResource {

    @Inject
    HobbitService hobbitService;

    @GET
    @Path("pay-me")
    public String payMe(@QueryParam("age") int age, @QueryParam("size") int size) {
        return hobbitService.payMe(age, size);
    }
}
