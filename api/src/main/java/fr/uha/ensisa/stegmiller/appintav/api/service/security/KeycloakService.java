package fr.uha.ensisa.stegmiller.appintav.api.service.security;

import fr.uha.ensisa.stegmiller.appintav.api.security.KeycloakInitializer;
import fr.uha.ensisa.stegmiller.appintav.api.security.KeycloakUser;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.rmi.UnexpectedException;
import java.util.List;

@Service
@Slf4j
public class KeycloakService {

    Keycloak keycloak;

    KeycloakInitializer keycloakInitializer;

    public KeycloakService(Keycloak keycloak, KeycloakInitializer keycloakInitializer) {
        this.keycloak = keycloak;
        this.keycloakInitializer = keycloakInitializer;
    }

    public String registerUser(KeycloakUser user) throws UnexpectedException {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(user.getUsername());
        userRepresentation.setFirstName(user.getName());
        userRepresentation.setLastName(user.getLastName());
        userRepresentation.setEnabled(true);

        CredentialRepresentation userCredentialRepresentation = new CredentialRepresentation();
        userCredentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        userCredentialRepresentation.setTemporary(false);
        userCredentialRepresentation.setValue(user.getPassword());
        userRepresentation.setCredentials(List.of(userCredentialRepresentation));

        keycloak.realm(KeycloakInitializer.getRealmId()).users().create(userRepresentation);

        return keycloak.realm(KeycloakInitializer.getRealmId()).users().search(user.getUsername()).get(0).getId();
    }
}
