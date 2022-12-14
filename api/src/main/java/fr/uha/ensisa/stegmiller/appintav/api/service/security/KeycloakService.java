package fr.uha.ensisa.stegmiller.appintav.api.service.security;

import fr.uha.ensisa.stegmiller.appintav.api.security.KeycloakInitializer;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import fr.uha.ensisa.stegmiller.appintav.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.rmi.UnexpectedException;
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

        CredentialRepresentation userCredentialRepresentation = new CredentialRepresentation();
        userCredentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        userCredentialRepresentation.setTemporary(false);
        userCredentialRepresentation.setValue(password);
        userRepresentation.setCredentials(List.of(userCredentialRepresentation));

        keycloak.realm(KeycloakInitializer.getRealmId()).users().create(userRepresentation);

        return keycloak.realm(KeycloakInitializer.getRealmId()).users().search(user.getUsername()).get(0).getId();
    }
}
