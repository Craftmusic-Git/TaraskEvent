package fr.uha.ensisa.stegmiller.appintav.model;

import fr.uha.ensisa.stegmiller.appintav.core.Model;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User extends Model<User> {

    public enum Status {
        UNKNOWN,
        CONNECTED,
        DISCONNECTED
    }

    @Column(name = "name")
    private String name;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "archived")
    private Boolean archived;

    @ManyToMany
    @JoinTable(
            name = "users_events_organized",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "events_id")
    )
    @ToString.Exclude
    private List<Event> eventOrganized;

    @ManyToMany
    @JoinTable(
            name = "users_favors",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "favors_id")
    )
    @ToString.Exclude
    private List<Favor> managedFavor;

    @Column(name = "status")
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
    public User update(User model) {
        super.update(model);
        if(model.getArchived() != null)
            this.archived = model.getArchived();
        if(model.getStatus() != null)
            this.status = model.getStatus();
        if(model.getName() != null)
            this.name = model.getName();
        if(model.getManagedFavor() != null)
            this.managedFavor = model.getManagedFavor();
        if(model.getEventOrganized() != null)
            this.eventOrganized = model.getEventOrganized();
        if(model.getFirstname() != null)
            this.firstname = model.getFirstname();
        if(model.getBirthdate() != null)
            this.birthdate = model.getBirthdate();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
