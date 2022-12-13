package fr.uha.ensisa.stegmiller.appintav.api.security;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KeycloakInitializer implements InitializingBean {


    private final Keycloak keycloak;

    private final KeycloakInitializerConfigurationProperties keycloakProperties;

    private static String REALM_ID;

    public static String getRealmId() {
        return REALM_ID;
    }

    public KeycloakInitializer(Keycloak keycloak, KeycloakInitializerConfigurationProperties keycloakInitializerConfigurationProperties) {
        this.keycloak = keycloak;
        this.keycloakProperties = keycloakInitializerConfigurationProperties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        REALM_ID = keycloakProperties.getRealm();
        log.info("Keycloak initialized successfully");
    }
}
