package fr.uha.ensisa.stegmiller.appintav.command.event;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class JoinEventCommandHandler implements Command.Handler<JoinEventCommand, Event>{

    @Autowired
    private EventService eventService;

    private static final Logger LOGGER = Logger.getLogger("JoinEventHandler");

    @Override
    public Event handle(JoinEventCommand command) {
        if(command.getEvent() == null){
            LOGGER.warning("Event is null");
            throw new Error("Event is null");
        }
        if(command.getUser() == null){
            LOGGER.warning( "User is null");
            throw new Error("User is null");
        }
        boolean isContain = false;
        for(var u : command.getEvent().getGuests()){
            if (u.getId().equals(command.getUser().getId())) {
                isContain = true;
                break;
            }
        }
        if(!isContain)
            eventService.addUserToEvent(command.getEvent(),command.getUser());
        LOGGER.info("User : "+command.getUser()+" is added to the event !");
        return command.getEvent();
    }
}
