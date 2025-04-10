package org.middle.earth.dto.keycloak;

public class KeycloakConfigDto {

  String issuer;

  String realm;

  String clientId;

  boolean ldap;

  public String getIssuer() {
    return issuer;
  }

  public void setIssuer(String issuer) {
    this.issuer = issuer;
  }

  public String getRealm() {
    return realm;
  }

  public void setRealm(String realm) {
    this.realm = realm;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public boolean isLdap() {
    return ldap;
  }

  public void setLdap(boolean ldap) {
    this.ldap = ldap;
  }
}
