package fr.uha.ensisa.stegmiller.appintav.command.event;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class CreateEventCommandHandler implements Command.Handler<CreateEventCommand, Event> {

    @Autowired
    EventService eventService;

    @Autowired
    JoinEventCommandHandler joinEventCommandHandler;


    private static final Logger LOGGER = Logger.getLogger("CreateEventHandler");

    @Override
    public Event handle(CreateEventCommand command) {
        if(command.getEvent() == null){
            LOGGER.warning("Event is null");
            throw new Error("Event is null");
        }
        if(command.getUser() == null){
            LOGGER.warning("User is null");
            throw new Error("User is null");
        }

        var joinCommand = new JoinEventCommand(eventService.addEvent(command.getEvent()),command.getUser());
        LOGGER.info("Event : " + joinCommand.getEvent().getName() + " created by " + command.getUser());
        return joinEventCommandHandler.handle(joinCommand);

    }
}
