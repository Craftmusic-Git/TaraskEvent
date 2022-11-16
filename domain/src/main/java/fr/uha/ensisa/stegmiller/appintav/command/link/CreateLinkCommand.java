package fr.uha.ensisa.stegmiller.appintav.command.link;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.Link;
import lombok.Getter;

@Getter
public class CreateLinkCommand implements Command<Link> {
    private Event event;

    public CreateLinkCommand(Event event){
        this.event = event;
    }
}
