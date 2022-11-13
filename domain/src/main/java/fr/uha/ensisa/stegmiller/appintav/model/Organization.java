package fr.uha.ensisa.stegmiller.appintav.model;

import fr.uha.ensisa.stegmiller.appintav.core.Model;
import lombok.Data;

import java.util.Date;

@Data
public class Organization extends Model {
    private Date date;
    private Integer capacity;
    private Boolean isOutside;
    private Integer ageLimit;
    private Scoring scoring;

    public Organization(){
        date = null;
        capacity = 5;
        isOutside = Boolean.FALSE;
        ageLimit = 18;
        scoring = new Scoring();
    }

    public Organization(Date date){
        this.date = date;
        capacity = 5;
        isOutside = Boolean.FALSE;
        ageLimit = 18;
        scoring = new Scoring();
    }
}
