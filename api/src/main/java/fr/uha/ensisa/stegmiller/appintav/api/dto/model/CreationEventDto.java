package fr.uha.ensisa.stegmiller.appintav.api.dto.model;

import fr.uha.ensisa.stegmiller.appintav.core.DTO;
import fr.uha.ensisa.stegmiller.appintav.core.DTOofModel;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreationEventDto extends DTO implements DTOofModel<Event> {

    private String name;

    @Override
    public Event modelOfDTO() {
        Event model = new Event();
        model.setId(this.id);
        model.setName(this.name);
        return model;
    }

    @Override
    public void dtoOfModel(Event model) {
        this.name = model.getName();
        this.id = model.getId();
    }
}
