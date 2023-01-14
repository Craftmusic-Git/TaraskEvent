package fr.uha.ensisa.stegmiller.appintav.command.event;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.Organisation;
import fr.uha.ensisa.stegmiller.appintav.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        if(event.getStatut() != Event.Statut.CONFIGURATION && event.getStatut() != Event.Statut.WAITING ) {
            LOGGER.warning("Event don't have the good statut");
        }
        switch (command.getPropertyType()){
            case DATE -> event.getOrganisation().setDate(LocalDate.parse((String) command.getInformation(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            case CAPACITY -> event.getOrganisation().setCapacity((Integer)command.getInformation());
            case EXTERN -> event.getOrganisation().setIsOutside((Boolean) command.getInformation());
            case LIMIT_AGE -> event.getOrganisation().setAgeLimit((Integer) command.getInformation());
        }

        if(eventIsComplete(event)) {
            event.setStatut(Event.Statut.WAITING);
        }
        if(event.getStatut() == Event.Statut.WAITING || event.getStatut() == Event.Statut.IN_PROGRESS || event.getStatut() == Event.Statut.READY){
            event.reScoring();
        }

        event = eventService.updateEvent(event);
        LOGGER.info("User : "+command.getOrganisator()+" update "+command.getPropertyType()+" : "+command.getInformation());


        return event;
    }

    private Boolean eventIsComplete(Event event){
        Organisation organisation = event.getOrganisation();
        return organisation.getIsOutside() != null && organisation.getDate() != null && organisation.getCapacity() != null && organisation.getAgeLimit() != null;
    }

    private Date integerToDate(Object number) {
        if (number.getClass() == Date.class)
            return (Date)number;
        if (number.getClass() == Integer.class)
            return Date.from(Instant.ofEpochSecond(((Integer) number).longValue()));
        if (number.getClass() == Long.class)
            return Date.from(Instant.ofEpochSecond((Long) number));
        return null;
    }
}
