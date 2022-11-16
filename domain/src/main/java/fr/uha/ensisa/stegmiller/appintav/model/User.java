package fr.uha.ensisa.stegmiller.appintav.model;

import fr.uha.ensisa.stegmiller.appintav.core.Model;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class User extends Model {

    public enum Status {
        UNKNOWN,
        CONNECTED,
        DISCONNECTED
    }

    private String name;
    private String firstname;
    private Date birthdate;
    private Boolean archived;
    private List<Event> eventOrganized;
    private List<Favor> managedFavor;
    private Status status;

    public User(){
        archived = Boolean.FALSE;
        this.archived = Boolean.FALSE;
        eventOrganized = new ArrayList<>();
        managedFavor = new ArrayList<>();
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
