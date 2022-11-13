package fr.uha.ensisa.stegmiller.appintav.model;

import fr.uha.ensisa.stegmiller.appintav.core.Model;
import fr.uha.ensisa.stegmiller.appintav.model.value.City;
import fr.uha.ensisa.stegmiller.appintav.model.value.CityCode;
import lombok.Data;

@Data
public class Address extends Model {
    private City city;
    private CityCode code;
    private Integer number;
    private String way;
}
