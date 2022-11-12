package fr.uha.ensisa.stegmiller.appintav.command.event;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import lombok.Getter;

@Getter
public class JoinEventCommand implements Command<Event> {

    private Event event;
    private User user;

    public JoinEventCommand(Event event, User user){
        this.user = user;
        this.event = event;
    }
}
