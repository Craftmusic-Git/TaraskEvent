package fr.uha.ensisa.stegmiller.appintav.api.dto.model;

import fr.uha.ensisa.stegmiller.appintav.core.DTO;
import fr.uha.ensisa.stegmiller.appintav.core.DTOofModel;
import fr.uha.ensisa.stegmiller.appintav.model.Organisation;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class OrganisationDto extends DTO implements DTOofModel<Organisation> {

    private Organisation.Day day;

    private Organisation.Weather weather;

    private Integer capacity;

    private Boolean isOutside;
    private Date date;

    private Integer ageLimit;

    @Override
    public Organisation modelOfDTO() {
        Organisation org = new Organisation();
        org.setId(id);
        org.setDate(date);
        org.setDay(day);
        org.setCapacity(capacity);
        org.setIsOutside(isOutside);
        org.setAgeLimit(ageLimit);
        return org;
    }

    @Override
    public void dtoOfModel(Organisation model) {
        id = model.getId();
        day = model.getDay();
        weather = model.getWeather();
        capacity = model.getCapacity();
        isOutside = model.getIsOutside();
        ageLimit = model.getAgeLimit();
        date = model.getDate();
    }
}
