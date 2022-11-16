package fr.uha.ensisa.stegmiller.appintav.command.link;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.Link;
import fr.uha.ensisa.stegmiller.appintav.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class CreateLinkCommandHandler implements Command.Handler<CreateLinkCommand, Link> {

    @Autowired
    private LinkService linkService;

    private final static Logger LOGGER = Logger.getLogger("CreateLinkHandler");

    @Override
    public Link handle(CreateLinkCommand command) {
        if(command.getEvent() == null){
            LOGGER.warning("Event is null");
            throw new Error("Event is null");
        }
        return linkService.createLink(command.getEvent());
    }
}
