package fr.uha.ensisa.stegmiller.appintav.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class User {

    public enum Status {
        UNKNOWN,
        CONNECTED,
        DISCONNECTED
    }

    private Long id;
    private String name;
    private String firstname;
    private Date birthdate;
    private Boolean archived;
    private List<Event> eventOrganized;
    private List<Favor> managedFavor;
    private Status status;

    public User(){
        archived = Boolean.FALSE;
    }

    public User(String name, String firstname, Date birthDate){
        this.name = name;
        this.firstname = firstname;
        this.birthdate = birthDate;
        this.archived = Boolean.FALSE;
        eventOrganized = new ArrayList<>();
        managedFavor = new ArrayList<>();
    }

    @Override
    public String toString(){
        return name + " " + firstname + " " + birthdate;
    }
}
