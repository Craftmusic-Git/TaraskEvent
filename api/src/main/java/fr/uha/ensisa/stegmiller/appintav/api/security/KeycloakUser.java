package fr.uha.ensisa.stegmiller.appintav.api.security;

import lombok.Data;

@Data
public class KeycloakUser {
    private String username;
    private String name;
    private String lastName;
    private String password;
    private boolean isAdmin;
}
