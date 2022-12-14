package fr.uha.ensisa.stegmiller.appintav.api.service.modelservices;

import fr.uha.ensisa.stegmiller.appintav.api.dto.model.UserRegistrationDto;
import fr.uha.ensisa.stegmiller.appintav.api.service.security.KeycloakService;
import fr.uha.ensisa.stegmiller.appintav.core.DTOServiceOfModel;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import fr.uha.ensisa.stegmiller.appintav.persistence.repositories.UserDAORepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserRegistrationDtoService implements DTOServiceOfModel<UserRegistrationDto, User> {

    @Autowired
    UserDAORepository userRepository;

    @Autowired
    KeycloakService keycloakService;

    @Override
    public UserRegistrationDto newInstanceOfDTO() {
        return new UserRegistrationDto();
    }

    @Override
    public JpaRepository<User, Long> getRepository() {
        return userRepository;
    }
}
