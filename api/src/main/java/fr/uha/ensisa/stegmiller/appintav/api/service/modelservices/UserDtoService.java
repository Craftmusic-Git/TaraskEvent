package fr.uha.ensisa.stegmiller.appintav.api.service.modelservices;

import fr.uha.ensisa.stegmiller.appintav.api.dto.model.UserDto;
import fr.uha.ensisa.stegmiller.appintav.core.DTOServiceOfModel;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import fr.uha.ensisa.stegmiller.appintav.persistence.repositories.UserDAORepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDtoService implements DTOServiceOfModel<UserDto, User> {

    final UserDAORepository userRepository;

    public UserDtoService(UserDAORepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto newInstanceOfDTO() {
        return new UserDto();
    }

    @Override
    public JpaRepository<User, Long> getRepository() {
        return userRepository;
    }
}
