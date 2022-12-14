package fr.uha.ensisa.stegmiller.appintav.api.dto.model;

import fr.uha.ensisa.stegmiller.appintav.core.DTO;
import fr.uha.ensisa.stegmiller.appintav.core.DTOofModel;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserRegistrationDto extends DTO implements DTOofModel<User> {

    private String username;

    private String name;

    private String lastName;

    private Date birthDate;

    private String password;

    @Override
    public User modelOfDTO() {
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setLastname(lastName);
        user.setBirthdate(birthDate);
        return user;
    }

    @Override
    public void dtoOfModel(User model) {
        username = model.getUsername();
        name = model.getName();
        lastName = model.getLastname();
        birthDate = model.getBirthdate();
    }
}
