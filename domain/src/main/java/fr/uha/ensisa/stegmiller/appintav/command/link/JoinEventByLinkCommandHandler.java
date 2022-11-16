package fr.uha.ensisa.stegmiller.appintav.command.link;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.command.event.JoinEventCommand;
import fr.uha.ensisa.stegmiller.appintav.command.event.JoinEventCommandHandler;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import fr.uha.ensisa.stegmiller.appintav.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

import static fr.uha.ensisa.stegmiller.appintav.model.Link.LINK_LENGTH;

@Component
public class JoinEventByLinkCommandHandler implements Command.Handler<JoinEventByLinkCommand,Event>{

    @Autowired
    LinkService linkService;
    @Autowired
    JoinEventCommandHandler joinEventCommandHandler;

    private static final Logger LOGGER = Logger.getLogger("JoinEventByLinkHandler");

    @Override
    public Event handle(JoinEventByLinkCommand command) {
        if(command.getLink() == null){
            LOGGER.warning("Link is null");
            throw new Error("Link is null");
        }
        if(command.getUser() == null){
            LOGGER.warning("User is null");
            throw new Error("User is null");
        }
        if(command.getUser().getStatus() == User.Status.UNKNOWN){
            LOGGER.warning("A unknown user try to join a event");
            throw new Error("A unknown user try to join a event");
        }
        if(command.getLink().getLink().length() != LINK_LENGTH){
            LOGGER.warning("Invalid link");
            throw new Error("Invalid link");
        }
        Event e = linkService.getLinkByString(command.getLink().getLink()).get().getEvent();
        return joinEventCommandHandler.handle(new JoinEventCommand(e,command.getUser()));
    }
}
