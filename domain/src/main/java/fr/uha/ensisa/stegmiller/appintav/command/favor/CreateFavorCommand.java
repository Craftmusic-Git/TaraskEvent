package fr.uha.ensisa.stegmiller.appintav.command.favor;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.Favor;
import lombok.Getter;

@Getter
public class CreateFavorCommand implements Command<Favor> {
    private Event event;
    private Favor favor;

    public CreateFavorCommand(Event event, Favor favor){
        this.event = event;
        this.favor = favor;
    }
}
