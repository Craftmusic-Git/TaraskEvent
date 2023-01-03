package fr.uha.ensisa.stegmiller.appintav.command.event;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.Address;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.Organisation;
import fr.uha.ensisa.stegmiller.appintav.service.EventService;
import fr.uha.ensisa.stegmiller.appintav.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

@Component
public class CreateEventCommandHandler implements Command.Handler<CreateEventCommand, Event> {

    @Autowired
    EventService eventService;

    @Autowired
    UserService userService;

    @Autowired
    JoinEventCommandHandler joinEventCommandHandler;


    private static final Logger LOGGER = Logger.getLogger("CreateEventHandler");

    @Override
    public Event handle(CreateEventCommand command) {
        if(command.getEvent() == null){
            LOGGER.warning("Event is null");
            throw new Error("Event is null");
        }
        if(command.getEvent().getName() == null){
            LOGGER.warning("Event name is null");
            throw new Error("Event name is null");
        }
        if(command.getUser() == null){
            LOGGER.warning("User is null");
            throw new Error("User is null");
        }

        Event event = command.getEvent();
        event.setStatut(Event.Statut.CONFIGURATION);
        event.setFavors(new HashMap<>());
        event.setGuests(new ArrayList<>());
        event.setOrganisation(new Organisation());
        event.setLocationAddress(new Address());

        var joinCommand = new JoinEventCommand(eventService.createEvent(event),command.getUser());
        command.getUser().getEventOrganized().add(joinCommand.getEvent());
        userService.updateUser(command.getUser());
        LOGGER.info("Event : " + joinCommand.getEvent().getName() + " created by " + command.getUser());
        return joinEventCommandHandler.handle(joinCommand);
    }
}
