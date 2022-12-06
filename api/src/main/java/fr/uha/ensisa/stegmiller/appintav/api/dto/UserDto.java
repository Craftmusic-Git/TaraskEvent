package fr.uha.ensisa.stegmiller.appintav.api.dto;

import fr.uha.ensisa.stegmiller.appintav.core.DTO;
import fr.uha.ensisa.stegmiller.appintav.core.DTOofModel;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDto extends DTO implements DTOofModel<User> {

    private String name;
    private String firstname;
    private Date birthdate;
    @Override
    public User modelOfDTO() {
        User rep = new User();
        rep.setId(id);
        rep.setName(name);
        rep.setFirstname(firstname);
        rep.setBirthdate(birthdate);
        return rep;
    }

    @Override
    public void dtoOfModel(User model) {
        this.name = model.getName();
        this.firstname = model.getFirstname();
        this.birthdate = model.getBirthdate();
    }
}
