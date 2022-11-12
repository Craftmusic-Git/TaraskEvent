package fr.uha.ensisa.stegmiller.appintav.model;

import lombok.Data;

import java.util.Date;

@Data
public class Organization {
    private Date date;
    private Integer capacity;
    private Boolean isOutside;
    private Integer ageLimit;

    public Organization(){
        date = null;
        capacity = 5;
        isOutside = Boolean.FALSE;
        ageLimit = 18;
    }

    public Organization(Date date){
        this.date = date;
        capacity = 5;
        isOutside = Boolean.FALSE;
        ageLimit = 18;
    }
}
