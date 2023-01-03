package fr.uha.ensisa.stegmiller.appintav.api.service.security;

import fr.uha.ensisa.stegmiller.appintav.api.security.KeycloakInitializer;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import fr.uha.ensisa.stegmiller.appintav.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.rmi.UnexpectedException;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class KeycloakService implements SecurityService {

    Keycloak keycloak;

    KeycloakInitializer keycloakInitializer;

    public KeycloakService(Keycloak keycloak, KeycloakInitializer keycloakInitializer) {
        this.keycloak = keycloak;
        this.keycloakInitializer = keycloakInitializer;
    }

    @Override
    public String registerUser(User user, String password) throws UnexpectedException {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(user.getUsername());
        userRepresentation.setFirstName(user.getName());
        userRepresentation.setLastName(user.getLastname());
        userRepresentation.setEnabled(true);

        RealmResource realmResource = keycloak.realm(KeycloakInitializer.getRealmId());

        CredentialRepresentation userCredentialRepresentation = new CredentialRepresentation();
        userCredentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        userCredentialRepresentation.setTemporary(false);
        userCredentialRepresentation.setValue(password);
        userRepresentation.setCredentials(List.of(userCredentialRepresentation));

        Response response = realmResource.users().create(userRepresentation);
        String userId = CreatedResponseUtil.getCreatedId(response);

        RoleRepresentation userRoleRepresentation = realmResource.roles().get("user").toRepresentation();
        realmResource.users().get(userId).roles().realmLevel().add(Collections.singletonList(userRoleRepresentation));

        return realmResource.users().search(user.getUsername()).get(0).getId();
    }
}
