package fr.uha.ensisa.stegmiller.appintav.command.favor;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.command.event.JoinEventCommand;
import fr.uha.ensisa.stegmiller.appintav.command.event.JoinEventCommandHandler;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.service.EventService;
import fr.uha.ensisa.stegmiller.appintav.service.FavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class FavorTakenByUserCommandHandler implements Command.Handler<FavorTakenByUserCommand, Event>{

    @Autowired
    FavorService favorService;

    @Autowired
    EventService eventService;

    @Autowired
    JoinEventCommandHandler joinEventCommandHandler;

    private static final Logger LOGGER = Logger.getLogger("FavorTakenByUserHandler");

    @Override
    public Event handle(FavorTakenByUserCommand command) {
        if(command.getEvent() == null){
            LOGGER.warning("Event is null");
            throw new Error("Event is null");
        }
        if(command.getUser() == null){
            LOGGER.warning( "User is null");
            throw new Error("User is null");
        }
        if(command.getFavor() == null){
            LOGGER.warning("Favor is null");
            throw new Error("Favor is null");
        }

        joinEventCommandHandler.handle(new JoinEventCommand(command.getEvent(),command.getUser()));
        var f = favorService.addUserToFavor(command.getFavor(),command.getUser());
        command.getEvent().getFavors().put(f,command.getUser());
        return command.getEvent();
    }
}
