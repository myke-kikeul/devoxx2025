package org.middle.earth.dto.keycloak;

import com.fasterxml.jackson.annotation.JsonAlias;

public class KeycloakTokenDto {

  @JsonAlias("access_token")
  public String accessToken;

  @JsonAlias("id_token")
  public String idToken;
}
