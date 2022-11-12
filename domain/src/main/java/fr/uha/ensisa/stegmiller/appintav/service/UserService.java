package fr.uha.ensisa.stegmiller.appintav.service;

import fr.uha.ensisa.stegmiller.appintav.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    User createUser(final User user);
    Optional<User> getUserById(final Long id);
    List<User> searchUserByNameFirstName(final String name, final String firstName);
    void removeUser(final User user);
}
