package fr.uha.ensisa.stegmiller.appintav.api.dto.model;

import fr.uha.ensisa.stegmiller.appintav.core.DTO;
import fr.uha.ensisa.stegmiller.appintav.core.DTOofModel;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDto extends DTO implements DTOofModel<User> {

    private String username;
    private String name;
    private String lastname;
    private Date birthdate;
    @Override
    public User modelOfDTO() {
        User rep = new User();
        rep.setId(id);
        rep.setName(name);
        rep.setLastname(lastname);
        rep.setBirthdate(birthdate);
        rep.setUsername(username);
        return rep;
    }

    @Override
    public void dtoOfModel(User model) {
        this.id = model.getId();
        this.name = model.getName();
        this.lastname = model.getLastname();
        this.birthdate = model.getBirthdate();
        this.username = model.getUsername();
    }
}
