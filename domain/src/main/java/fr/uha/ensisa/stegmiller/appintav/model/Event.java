package fr.uha.ensisa.stegmiller.appintav.model;

import lombok.Data;

import java.util.*;

@Data
public class Event {

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

    private Long id;
    private String name;
    private Statut statut;
    private Organization organization;
    private Address locationAddress;
    private List<User> guests;
    private Map<Favor,User> favors;

    public Event(){
        guests = new ArrayList<>();
        favors = new HashMap<>();
    }
}
