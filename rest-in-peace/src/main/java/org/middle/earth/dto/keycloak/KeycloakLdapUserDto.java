package org.middle.earth.dto.keycloak;

public class KeycloakLdapUserDto extends KeycloakUserDto {
  private boolean available;

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }
}
