package fr.uha.ensisa.stegmiller.appintav.command.event;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import lombok.Getter;

@Getter
public class ValidateEventCommand implements Command<Event> {
    private User organizator;
    private Event event;

    public ValidateEventCommand(User organizator, Event event){
        this.organizator = organizator;
        this.event = event;
    }
}
