package fr.uha.ensisa.stegmiller.appintav.model;

import fr.uha.ensisa.stegmiller.appintav.core.Model;
import lombok.Data;

import java.util.*;

@Data
public class Event extends Model {

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

    private String name;
    private Statut statut;
    private Organization organization;
    private Address locationAddress;
    private List<User> guests;
    private Map<Favor,User> favors;

    public Event(){
        guests = new ArrayList<>();
        favors = new HashMap<>();
        organization = new Organization();
    }

    public Event(String name, Address locationAddress){
        guests = new ArrayList<>();
        favors = new HashMap<>();
        organization = new Organization();
        this.name = name;
        this.locationAddress = locationAddress;
    }

    public void reScoring(){
        Calendar c = Calendar.getInstance();
        c.setTime(organization.getDate());
        int dayOfTheWeek = c.get(Calendar.DAY_OF_WEEK);
        int dateScore = 0;
        if(dayOfTheWeek == 6 || dayOfTheWeek == 7)
            dateScore += 3;
        int monthOfTheYear = c.get(Calendar.MONTH);
        if(monthOfTheYear == 7 || monthOfTheYear == 8 || monthOfTheYear == 11){
            dateScore += 2;
        }
        organization.getScoring().setDateScore(dateScore);
    }
}
