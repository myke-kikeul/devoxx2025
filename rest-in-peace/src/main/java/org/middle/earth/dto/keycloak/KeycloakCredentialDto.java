package org.middle.earth.dto.keycloak;

public class KeycloakCredentialDto {

  private String type;

  private String value;

  private boolean temporary;

  public KeycloakCredentialDto(String type, String value, boolean temporary) {
    this.type = type;
    this.value = value;
    this.temporary = temporary;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public boolean isTemporary() {
    return temporary;
  }

  public void setTemporary(boolean temporary) {
    this.temporary = temporary;
  }
}
