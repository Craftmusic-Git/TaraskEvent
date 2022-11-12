package fr.uha.ensisa.stegmiller.appintav.command.favor;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.Favor;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import lombok.Getter;

@Getter
public class FavorTakenByUserCommand implements Command<Event> {
    private User user;
    private Event event;
    private Favor favor;

    public FavorTakenByUserCommand(User user, Event event, Favor favor){
        this.user = user;
        this.event = event;
        this.favor = favor;
    }
}
