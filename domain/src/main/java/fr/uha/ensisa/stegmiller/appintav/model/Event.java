package fr.uha.ensisa.stegmiller.appintav.model;

import fr.uha.ensisa.stegmiller.appintav.core.Model;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "events")
public class Event extends Model<Event> {

    public enum Statut{
        CONFIGURATION("Organisation"), // ORGANIZATION AND CONFIGURATION STEP
        WAITING("En attente"),
        READY("Prêt"),
        IN_PROGRESS("En cours"),
        ERROR("Erreur");

        private String label;

        Statut(String status) {
            this.label = status;
        }

        public static Optional<Statut> get(final String status){
            return Arrays.stream(Statut.values())
                    .filter(e -> e.label.equals(status))
                    .findFirst();
        }
    }

    @Column(name = "name")
    private String name;

    @Column(name = "statut")
    private Statut statut;

    @OneToOne
    @JoinColumn(name = "organization_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Organisation organisation;

    @ManyToOne
    @JoinColumn(name = "location_address_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Address locationAddress;

    @ManyToMany
    @JoinTable(
            name = "guests",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id")
    )
    private List<User> guests;

    @OneToMany
    @JoinTable(
            name = "favors_managers",
            joinColumns = {@JoinColumn(name = "events_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "users_id", referencedColumnName = "id")}
    )
    @MapKeyJoinColumn(name = "favors_id")
    private Map<Favor,User> favors;

    public Event(){
        guests = new ArrayList<>();
        favors = new HashMap<>();
        organisation = new Organisation();
    }

    public Event(String name, Address locationAddress){
        guests = new ArrayList<>();
        favors = new HashMap<>();
        organisation = new Organisation();
        this.name = name;
        this.locationAddress = locationAddress;
    }

    public void reScoring(){
        Calendar c = Calendar.getInstance();
        c.setTime(organisation.getDate());
        int dayOfTheWeek = c.get(Calendar.DAY_OF_WEEK);
        int dateScore = 0;
        if(dayOfTheWeek == 6 || dayOfTheWeek == 7)
            dateScore += 3;
        int monthOfTheYear = c.get(Calendar.MONTH);
        if(monthOfTheYear == 7 || monthOfTheYear == 8 || monthOfTheYear == 11){
            dateScore += 2;
        }
        organisation.getScoring().setDateScore(dateScore);
    }

    @Override
    public Event update(Event model) {
        super.update(model);
        if(model.getFavors() != null)
            this.favors = model.getFavors();
        if(model.getGuests() != null)
            this.guests = model.getGuests();
        if(model.getName() != null)
            this.name = model.getName();
        if(model.getStatut() != null)
            this.statut = model.getStatut();
        if(model.getLocationAddress() != null)
            this.locationAddress = model.getLocationAddress();
        if(model.getOrganisation() != null)
            this.organisation = model.getOrganisation();
        return this;
    }
}
