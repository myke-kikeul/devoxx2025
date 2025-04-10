package org.middle.earth;

import io.quarkus.oidc.runtime.OidcJwtCallerPrincipal;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
import jakarta.json.JsonNumber;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.middle.earth.service.HobbitService;

@Path("/api/hobbit/secured")
public class HobbitSecuredResource {

    @Inject
    SecurityIdentity identity;

    @Inject
    HobbitService hobbitService;

    @GET
    @Path("pay-me")
    public String payMe() {
        OidcJwtCallerPrincipal principal = (OidcJwtCallerPrincipal) identity.getPrincipal();
        String race = (String) principal.getClaims().getClaimValue("race");
        if (!"HOBBIT".equalsIgnoreCase(race)) {
            throw new SecurityException("Race is not HOBBIT");
        }
        JsonNumber age = (JsonNumber) principal.getClaims().getClaimValue("age");
        JsonNumber size = (JsonNumber) principal.getClaims().getClaimValue("size");
        return hobbitService.payMe(age != null ? age.intValue() : 0, size != null ? size.intValue() : 0);
    }
}
