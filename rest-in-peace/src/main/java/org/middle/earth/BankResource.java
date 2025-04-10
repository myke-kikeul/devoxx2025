package org.middle.earth;

import io.quarkus.oidc.runtime.OidcJwtCallerPrincipal;
import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.middle.earth.rest.client.DwarfRestClient;
import org.middle.earth.rest.client.ElfRestClient;
import org.middle.earth.rest.client.HobbitRestClient;

@Path("bank")
@Authenticated
public class BankResource {

    @Inject
    @RestClient
    HobbitRestClient hobbitRestClient;

    @Inject
    @RestClient
    DwarfRestClient dwarfRestClient;

    @Inject
    @RestClient
    ElfRestClient elfRestClient;

    @Inject
    SecurityIdentity identity;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("unsecure/pay-me")
    public String payMe(@QueryParam("race") RaceType raceType, @QueryParam("age") int age, @QueryParam("waist") int waist, @QueryParam("size") int size) {
        return switch (raceType) {
            case HOBBIT -> hobbitRestClient.unsecuredPayMe(age, size);
            case DWARF -> dwarfRestClient.unsecuredPayMe(age, waist);
            case ELF -> elfRestClient.unsecuredPayMe(age, waist);
            default -> throw new IllegalStateException("Unexpected value: " + raceType);
        };
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("secure/pay-me")
    public String payMe(@Context SecurityContext sec) {
        OidcJwtCallerPrincipal principal = (OidcJwtCallerPrincipal) identity.getPrincipal();
        String race = (String) principal.getClaims().getClaimValue("race");
        return switch (race) {
            case "HOBBIT" -> hobbitRestClient.securedPayMe();
            case "DWARF" -> dwarfRestClient.securedPayMe();
            case "ELF" -> elfRestClient.securedPayMe();
            default -> throw new IllegalStateException("Unexpected value: " + race);
        };
    }
}
