package org.middle.earth.rest.client;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.middle.earth.dto.keycloak.KeycloakGroupDto;
import org.middle.earth.dto.keycloak.KeycloakRoleDto;
import org.middle.earth.dto.keycloak.KeycloakTokenDto;
import org.middle.earth.dto.keycloak.KeycloakUserDto;

import java.util.List;

@RegisterRestClient(configKey = "keycloak-admin")
public interface KeycloakRestClient {

  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/realms/middle-earth/protocol/openid-connect/token")
  KeycloakTokenDto getAdminToken(@FormParam("client_id") String clientId,
                                 @FormParam("username") String username,
                                 @FormParam("password") String password,
                                 @FormParam("grant_type") String grantType,
                                 @FormParam("scope") String scope);

  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/realms/middle-earth/protocol/openid-connect/revoke")
  KeycloakTokenDto revokeToken(@FormParam("client_id") String clientId,
                               @FormParam("token_type_hint") String tokenTypeHint,
                               @FormParam("token") String token);

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/admin/realms/middle-earth/users/{id}")
  Response updateUser(@HeaderParam("Authorization") String bearerToken, @PathParam("id") String id, KeycloakUserDto keycloakUserDto) throws WebApplicationException;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/admin/realms/middle-earth/users")
  List<KeycloakUserDto> findUser(@HeaderParam("Authorization") String bearerToken, @QueryParam("username") String login, @QueryParam("exact") boolean exactValue);


  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/admin/realms/middle-earth/users")
  Response addUser(@HeaderParam("Authorization") String bearerToken, KeycloakUserDto utilisateur) throws WebApplicationException;



  @GET
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/admin/realms/middle-earth/users")
  List<KeycloakUserDto> getUsers(@HeaderParam("Authorization") String bearerToken, @QueryParam("first") int pageNumber, @QueryParam("max") int pageSize, @QueryParam("search") String searchString);

  @GET
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/admin/realms/middle-earth/users/count")
  int countUsers(@HeaderParam("Authorization") String bearerToken);


  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/admin/realms/middle-earth/users")
  List<KeycloakUserDto> findEmail(@HeaderParam("Authorization") String bearerToken, @QueryParam("email") String email, @QueryParam("exact") boolean exactValue);

  @DELETE
  @Path("/admin/realms/middle-earth/users/{id}")
  Response deleteUser(@HeaderParam("Authorization") String bearerToken, @PathParam("id") String id);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/admin/realms/middle-earth/users/{id}/logout")
  Response logoutUser(@HeaderParam("Authorization") String bearerToken, @PathParam("id") String id);

  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/realms/middle-earth/protocol/openid-connect/revoke")
  Response revoke(@FormParam("client_id") String clientId,
                  @FormParam("token_type_hint") String tokenTypeHint,
                  @FormParam("token") String token);

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/admin/realms/middle-earth/roles/{role-name}")
  KeycloakRoleDto getRole(@HeaderParam("Authorization") String bearerToken, @PathParam("role-name") String roleName);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/admin/realms/middle-earth/roles")
  Response createRole(@HeaderParam("Authorization") String bearerToken, @RequestBody KeycloakRoleDto keycloakRoleDto);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/admin/realms/middle-earth/users/{id}/role-mappings/realm")
  Response addRole(@HeaderParam("Authorization") String bearerToken, @PathParam("id") String id, @RequestBody List<KeycloakRoleDto> keycloakRoleDtos);

  @DELETE
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/admin/realms/middle-earth/users/{id}/role-mappings/realm")
  Response removeRole(@HeaderParam("Authorization") String bearerToken, @PathParam("id") String id, @RequestBody List<KeycloakRoleDto> keycloakRoleDtos);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/admin/realms/middle-earth/groups")
  Response createGroup(@HeaderParam("Authorization") String bearerToken, @RequestBody KeycloakGroupDto keycloakGroupDto);

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/admin/realms/middle-earth/groups/{id}")
  Response updateGroup(@HeaderParam("Authorization") String bearerToken, @PathParam("id") String id, @RequestBody KeycloakGroupDto keycloakGroupDto);

  @DELETE
  @Path("/admin/realms/middle-earth/groups/{id}")
  Response deleteGroup(@HeaderParam("Authorization") String bearerToken, @PathParam("id") String id);

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/admin/realms/middle-earth/groups")
  List<KeycloakGroupDto> getGroup(@HeaderParam("Authorization") String bearerToken, @QueryParam("search") String name, @QueryParam("exact") boolean exactValue);

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/admin/realms/middle-earth/users/{id}/groups")
  List<KeycloakGroupDto> getUserGroups(@HeaderParam("Authorization") String bearerToken, @PathParam("id") String id);

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/admin/realms/middle-earth/users/{id}/groups/{groupId}")
  Response addGroup(@HeaderParam("Authorization") String bearerToken, @PathParam("id") String id, @PathParam("groupId") String groupId);

  @DELETE
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/admin/realms/middle-earth/users/{id}/groups/{groupId}")
  Response removeGroup(@HeaderParam("Authorization") String bearerToken, @PathParam("id") String id, @PathParam("groupId") String groupId);

}
