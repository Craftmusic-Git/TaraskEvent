package fr.uha.ensisa.stegmiller.appintav.api.service;

import fr.uha.ensisa.stegmiller.appintav.api.service.security.KeycloakService;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import fr.uha.ensisa.stegmiller.appintav.persistence.repositories.UserDAORepository;
import fr.uha.ensisa.stegmiller.appintav.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    UserDAORepository userDAO;

    KeycloakService keycloakService;

    public UserServiceImpl(UserDAORepository userDAO, KeycloakService keycloakService){
        this.userDAO = userDAO;
        this.keycloakService = keycloakService;
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
    public List<User> searchUserByNameFirstName(String name, String firstName) {
        return userDAO.findUsersByNameAndFirstname(name, firstName);
    }

    @Override
    public void removeUser(User user) {
        userDAO.delete(user);
    }
}
