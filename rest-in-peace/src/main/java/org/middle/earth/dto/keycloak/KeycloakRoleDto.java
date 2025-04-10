package org.middle.earth.dto.keycloak;

public class KeycloakRoleDto {

  private String id;

  private String name;

  public KeycloakRoleDto() {
  }

  public KeycloakRoleDto(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
