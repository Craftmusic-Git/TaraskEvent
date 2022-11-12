package fr.uha.ensisa.stegmiller.appintav.command.event;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import lombok.Getter;

@Getter
public class CreateEventCommand implements Command<Event> {

    private User user;
    private Event event;

    public CreateEventCommand(User user, Event event){
        this.user = user;
        this.event = event;
    }
}
