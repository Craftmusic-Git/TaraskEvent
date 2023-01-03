package fr.uha.ensisa.stegmiller.appintav.api.service;

import fr.uha.ensisa.stegmiller.appintav.model.User;
import fr.uha.ensisa.stegmiller.appintav.persistence.repositories.UserDAORepository;
import fr.uha.ensisa.stegmiller.appintav.service.UserService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.IDToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    UserDAORepository userDAO;

    public UserServiceImpl(UserDAORepository userDAO){
        this.userDAO = userDAO;
    }

    @Override
    public User createUser(final User user) {
        return userDAO.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public List<User> searchUserByNameLastName(String name, String lastName) {
        return userDAO.findUsersByNameAndLastname(name, lastName);
    }

    @Override
    public void removeUser(User user) {
        userDAO.delete(user);
    }

    @Override
    public User updateUser(User user) {
        return userDAO.save(user);
    }

    public Optional<User> getCurrentUser() {
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        Principal principal = (Principal) authentication.getPrincipal();

        String userIdByToken = "";

        if (principal instanceof KeycloakPrincipal) {
            KeycloakPrincipal<KeycloakSecurityContext> kPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
            IDToken token = kPrincipal.getKeycloakSecurityContext().getToken();
            userIdByToken = token.getSubject();
        }

        Optional<User> user = null;
        if (!userIdByToken.isEmpty()) {
            user = userDAO.findByKeycloakId(userIdByToken);
        }

        return user;
    }
}
