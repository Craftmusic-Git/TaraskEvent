package fr.uha.ensisa.stegmiller.appintav.command.favor;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.Favor;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import lombok.Getter;

@Getter
public class UpdateFavorProgressCommand implements Command<Event> {
    private Event event;
    private Favor favor;
    private User user;
    private int progress;

    public UpdateFavorProgressCommand(Event event, Favor favor, User user, int progress){
        this.event = event;
        this.favor = favor;
        this.user = user;
        this.progress = progress;
    }
}
