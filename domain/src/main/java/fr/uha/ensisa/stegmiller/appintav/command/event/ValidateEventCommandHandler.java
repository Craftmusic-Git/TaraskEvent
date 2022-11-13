package fr.uha.ensisa.stegmiller.appintav.command.event;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ValidateEventCommandHandler implements Command.Handler<ValidateEventCommand, Event> {

    @Autowired
    EventService eventService;

    private static final Logger LOGGER = Logger.getLogger("ValidateEventHandler");

    @Override
    public Event handle(ValidateEventCommand command) {
        if(command.getEvent() == null){
            LOGGER.warning("Event is null");
            throw new Error("Event is null");
        }
        if(command.getOrganizator() == null){
            LOGGER.warning("Organizator is null");
            throw new Error("Organizator is null");
        }
        if(command.getEvent().getStatut() == Event.Statut.WAITING | command.getEvent().getStatut() == Event.Statut.IN_PROGRESS){
            boolean isAllValidate = true;
            for(var entry : command.getEvent().getFavors().entrySet()) {
                if (entry.getKey().getProgress() != 100) {
                    isAllValidate = false;
                    break;
                }
            }
            if(isAllValidate) {
                command.getEvent().setStatut(Event.Statut.READY);
                eventService.updateEvent(command.getEvent());
                LOGGER.info("User : " + command.getOrganizator() + " validate event " + command.getEvent().getName());
            } else {
                command.getEvent().setStatut(Event.Statut.IN_PROGRESS);
                eventService.updateEvent(command.getEvent());
            }
        }
        return command.getEvent();
    }
}
