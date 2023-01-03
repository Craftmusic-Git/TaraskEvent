package fr.uha.ensisa.stegmiller.appintav.api.dto.model;

import fr.uha.ensisa.stegmiller.appintav.core.DTO;
import fr.uha.ensisa.stegmiller.appintav.core.DTOofModel;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EventDto extends DTO implements DTOofModel<Event> {

    private String name;
    private Event.Statut statut;
    private OrganisationDto organisation;

    @Override
    public Event modelOfDTO() {
        Event event = new Event();
        event.setId(id);
        event.setName(name);
        event.setStatut(statut);
        return event;
    }

    @Override
    public void dtoOfModel(Event model) {

    }
}
