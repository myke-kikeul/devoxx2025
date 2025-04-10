package org.middle.earth.dto.keycloak;

import java.util.Map;

public class KeycloakGroupDto {

  private String id;

  private String name;

  private Map<String, String[]> attributes;

  public KeycloakGroupDto() {
  }

  public KeycloakGroupDto(String name) {
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

  public Map<String, String[]> getAttributes() {
    return attributes;
  }

  public void setAttributes(Map<String, String[]> attributes) {
    this.attributes = attributes;
  }
}
