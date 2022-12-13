package fr.uha.ensisa.stegmiller.appintav.api.dto.model;

import fr.uha.ensisa.stegmiller.appintav.core.DTO;
import fr.uha.ensisa.stegmiller.appintav.core.DTOofModel;
import fr.uha.ensisa.stegmiller.appintav.model.Event;

public class ReducedEventDto extends DTO implements DTOofModel<Event> {

    private String name;
    private Integer nbGuest;
    private Integer maxGuest;
    private Event.Statut statut;

    public ReducedEventDto(){
        statut = null;
    }

    @Override
    public Event modelOfDTO() {
        Event event = new Event();
        event.setName(name);
        event.setStatut(statut);
        return event;
    }

    @Override
    public void dtoOfModel(Event model) {
        id = model.getId();
        name = model.getName();
        maxGuest = model.getOrganization().getCapacity();
        nbGuest = model.getGuests().size();
    }
}
