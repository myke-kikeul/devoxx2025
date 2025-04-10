package org.middle.earth.rest.client;

import io.quarkus.oidc.token.propagation.common.AccessToken;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "elf-api")
@AccessToken
public interface ElfRestClient {
    @GET
    @Path("/api/elf/secured/pay-me")
    String securedPayMe();

    @GET
    @Path("/api/elf/unsecured/pay-me")
    String unsecuredPayMe(@QueryParam("age") int age, @QueryParam("waist") int waist);
}
