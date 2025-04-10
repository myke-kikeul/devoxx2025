package org.middle.earth.rest.client;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.middle.earth.dto.KeycloakUserInfoDto;


import java.util.List;

@RegisterRestClient(configKey = "keycloak-admin")
public interface KeycloakRestClient {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/realms/middle-earth/protocol/openid-connect/userinfo")
  KeycloakUserInfoDto getUserInfo(@HeaderParam("Authorization") String bearerToken);

}
