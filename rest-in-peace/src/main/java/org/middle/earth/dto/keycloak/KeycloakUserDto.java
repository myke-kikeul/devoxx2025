package org.middle.earth.dto.keycloak;

import java.util.Map;

public class KeycloakUserDto {

  private String id;

  private String username;

  private String firstName;

  private String lastName;

  private String email;

  private boolean enabled;

  private KeycloakCredentialDto[] credentials;

  private Map<String, String[]> attributes;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public KeycloakCredentialDto[] getCredentials() {
    return credentials;
  }

  public void setCredentials(KeycloakCredentialDto[] credentials) {
    this.credentials = credentials;
  }

  public Map<String, String[]> getAttributes() {
    return attributes;
  }

  public void setAttributes(Map<String, String[]> attributes) {
    this.attributes = attributes;
  }
}
