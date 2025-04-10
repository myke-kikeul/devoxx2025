package org.middle.earth.web;

import io.quarkus.oidc.runtime.OidcJwtCallerPrincipal;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
import jakarta.json.JsonNumber;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.middle.earth.service.ElfService;

@Path("/api/elf/secured")
public class ElfSecuredResource {

    @Inject
    SecurityIdentity identity;

    @Inject
    ElfService elfService;

    @GET
    @Path("pay-me")
    public String payMe() {
        OidcJwtCallerPrincipal principal = (OidcJwtCallerPrincipal) identity.getPrincipal();
        String race = (String) principal.getClaims().getClaimValue("race");
        if (!"ELF".equalsIgnoreCase(race)) {
            throw new SecurityException("Race is not ELF");
        }
        JsonNumber age = (JsonNumber) principal.getClaims().getClaimValue("age");
        JsonNumber waist = (JsonNumber) principal.getClaims().getClaimValue("waist");
        return elfService.payMe(age.intValue(), waist.intValue());
    }
}
