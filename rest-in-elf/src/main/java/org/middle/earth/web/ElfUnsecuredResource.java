package org.middle.earth.web;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.middle.earth.service.ElfService;

@Path("/api/elf/unsecured")
public class ElfUnsecuredResource {

    @Inject
    ElfService elfService;

    @GET
    @Path("pay-me")
    public String payMe(@QueryParam("age") int age, @QueryParam("waist") int waist) {
        return elfService.payMe(age, waist);
    }
}
