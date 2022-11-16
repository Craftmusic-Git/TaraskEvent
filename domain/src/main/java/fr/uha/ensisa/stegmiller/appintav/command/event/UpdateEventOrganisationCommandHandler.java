package fr.uha.ensisa.stegmiller.appintav.command.event;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.Organization;
import fr.uha.ensisa.stegmiller.appintav.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.logging.Logger;

@Component
public class UpdateEventOrganisationCommandHandler implements Command.Handler<UpdateEventOrganisationCommand, Event> {

    @Autowired
    private EventService eventService;

    private static final Logger LOGGER = Logger.getLogger("UpdateEventOrganisationHandler");

    @Override
    public Event handle(UpdateEventOrganisationCommand command) {
        if(command.getOrganisator() == null){
            LOGGER.warning("Organisator (type : User) is null ");
            throw new Error("Organisator (type : User) is null ");
        }
        if(command.getEvent() == null){
            LOGGER.warning("Event is null");
            throw new Error("Event is null");
        }
        if(command.getInformation() == null){
            LOGGER.warning("Information is null");
            throw new Error("Information is null");
        }
        if(command.getPropertyType() == null){
            LOGGER.warning("Property type is not defined");
            throw new Error("Property type is not defined");
        }
        Event event = command.getEvent();
        if(event.getStatut() != Event.Statut.CONFIGURATION || event.getStatut() != Event.Statut.WAITING ) {
            LOGGER.warning("Event don't have the good statut");
        }
        switch (command.getPropertyType()){
            case DATE -> event.getOrganization().setDate((Date)command.getInformation());
            case CAPACITY -> event.getOrganization().setCapacity((Integer)command.getInformation());
            case EXTERN -> event.getOrganization().setIsOutside((Boolean) command.getInformation());
            case LIMIT_AGE -> event.getOrganization().setAgeLimit((Integer) command.getInformation());
        }

        event = eventService.updateEvent(event);
        LOGGER.info("User : "+command.getOrganisator()+" update "+command.getPropertyType()+" : "+command.getInformation());

        if(eventIsComplete(event)) {
            event.setStatut(Event.Statut.WAITING);
        }
        if(event.getStatut() == Event.Statut.WAITING || event.getStatut() == Event.Statut.IN_PROGRESS || event.getStatut() == Event.Statut.READY){
            event.reScoring();
        }

        return event;
    }

    private Boolean eventIsComplete(Event event){
        Organization organization = event.getOrganization();
        return organization.getIsOutside() != null && organization.getDate() != null && organization.getCapacity() != null && organization.getAgeLimit() != null;
    }
}
