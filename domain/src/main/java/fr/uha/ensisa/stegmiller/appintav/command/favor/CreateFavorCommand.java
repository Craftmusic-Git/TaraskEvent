package fr.uha.ensisa.stegmiller.appintav.command.favor;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.Favor;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import lombok.Getter;

@Getter
public class CreateFavorCommand implements Command<Favor> {
    private Event event;
    private Favor favor;
    private User organizator;

    public CreateFavorCommand(Event event, Favor favor){
        this.event = event;
        this.favor = favor;
    }

    public CreateFavorCommand(Event event, Favor favor, User organizator){
        this.event = event;
        this.favor = favor;
        this.organizator = organizator;
    }
}
