package org.middle.earth;

import io.quarkus.logging.Log;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.middle.earth.dto.keycloak.KeycloakTokenDto;
import org.middle.earth.dto.keycloak.KeycloakUserDto;
import org.middle.earth.rest.client.KeycloakRestClient;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Path("dungeon")
@Authenticated
public class DungeonResource {

    public static final String BEARER = "Bearer ";

    public static final int NB_CELLS = 10000;

    public static final String CELL_ATTRIBUT = "cell";

    @Inject
    @RestClient
    KeycloakRestClient keycloakRestClient;

    @ConfigProperty(name = "keycloak.administration.client-id")
    String adminClientId;

    @ConfigProperty(name = "keycloak.administration.login")
    String administrationLogin;

    @ConfigProperty(name = "keycloak.administration.password")
    String administrationPwd;

    @GET
    @Path("career")
    public String getCareer(@Context SecurityContext securityContext) {
        Map<String, String[]> attributes = getUserAttributes(securityContext.getUserPrincipal().getName());
        String[] cells = attributes.get(CELL_ATTRIBUT);
        return MessageFormat.format("Congratulation, you visited {0} cells in your career!\nHere is the list : {1}", cells.length,
                Arrays.stream(cells).map(Object::toString).collect(Collectors.joining(",")));
    }

    @GET
    @Path("generate-career")
    public Response generateCareer(@Context SecurityContext securityContext) {
        Map<String, String[]> attributes = new HashMap<>();
        List<String> cells = IntStream.range(0, NB_CELLS).mapToObj(String::valueOf).toList();
        attributes.put(CELL_ATTRIBUT, cells.toArray(String[]::new));
        updateUserAttributes(securityContext.getUserPrincipal().getName(), attributes);
        return Response.ok().build();
    }

    private String getKeycloakToken() {
        KeycloakTokenDto adminToken = keycloakRestClient.getAdminToken(adminClientId, administrationLogin, administrationPwd, "password", "openid");
        return BEARER + adminToken.accessToken;
    }

    private Map<String, String[]> getUserAttributes(String login) {
        String keycloakToken = getKeycloakToken();
        List<KeycloakUserDto> keycloakUserDtos = keycloakRestClient.findUser(keycloakToken, login, true);
        if (keycloakUserDtos == null || keycloakUserDtos.isEmpty()) {
            Log.warnf("Keycloak user {0} not found", login);
            return Map.of();
        }
        KeycloakUserDto keycloakUserDto = keycloakUserDtos.getFirst();
        if (keycloakUserDto.getAttributes() != null) {
            revokeToken(keycloakToken);
            return keycloakUserDto.getAttributes();
        }
        return Map.of();
    }

    private void updateUserAttributes(String login, Map<String, String[]> attributes) {
        String keycloakToken = getKeycloakToken();
        List<KeycloakUserDto> keycloakUserDtos = keycloakRestClient.findUser(keycloakToken, login, true);
        if (keycloakUserDtos == null || keycloakUserDtos.isEmpty()) {
            Log.warnf("Keycloak user {0} not found", login);
            return;
        }
        KeycloakUserDto keycloakUserDto = keycloakUserDtos.getFirst();
        if (keycloakUserDto.getAttributes() != null) {
            keycloakUserDto.getAttributes().putAll(attributes);
        } else {
            keycloakUserDto.setAttributes(attributes);
        }
        keycloakRestClient.updateUser(keycloakToken, keycloakUserDto.getId(), keycloakUserDto);
        revokeToken(keycloakToken);
    }

    private void revokeToken(String keycloakToken) {
        keycloakRestClient.revokeToken(adminClientId, "access_token", keycloakToken);
    }
}
