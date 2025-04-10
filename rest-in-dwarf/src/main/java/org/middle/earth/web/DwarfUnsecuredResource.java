package org.middle.earth.web;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.middle.earth.service.DwarfService;

@Path("/api/dwarf/unsecured")
public class DwarfUnsecuredResource {

    @Inject
    DwarfService dwarfService;

    @GET
    @Path("pay-me")
    public String payMe(@QueryParam("age") int age, @QueryParam("waist") int waist) {
        return dwarfService.payMe(age, waist);
    }
}
