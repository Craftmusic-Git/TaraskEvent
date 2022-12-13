package fr.uha.ensisa.stegmiller.appintav.api.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "keycloak-initializr")
@Component
public class KeycloakInitializerConfigurationProperties {

    private String realm;

    private String serverUrl;

    private String resource;

    private String adminUsername;

    private String adminPassword;

    private String clientId;
}
