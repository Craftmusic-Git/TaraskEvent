package fr.uha.ensisa.stegmiller.appintav.command.favor;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.command.event.ValidateEventCommand;
import fr.uha.ensisa.stegmiller.appintav.command.event.ValidateEventCommandHandler;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.service.EventService;
import fr.uha.ensisa.stegmiller.appintav.service.FavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class UpdateFavorProgressCommandHandler implements Command.Handler<UpdateFavorProgressCommand, Event> {

    @Autowired
    FavorService favorService;

    @Autowired
    EventService eventService;

    @Autowired
    ValidateEventCommandHandler validateEventCommandHandler;

    private static final Logger LOGGER = Logger.getLogger("UpdateFavorHandler");

    @Override
    public Event handle(UpdateFavorProgressCommand command) {
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
        if(!(command.getEvent().getStatut() == Event.Statut.IN_PROGRESS | command.getEvent().getStatut() == Event.Statut.WAITING))
            throw new Error("This event can't be modified, it is in : "+command.getEvent().getStatut());
        boolean isContain = false;
        for(var entry : command.getEvent().getFavors().entrySet()){
            if(entry.getKey().getId().equals(command.getFavor().getId())){
                isContain = true;
                break;
            }
        }
        if(!isContain) {
            throw new Error("This favor is not in this event");
        }
        for(var f : command.getEvent().getFavors().entrySet()){
            if(f.getKey().getId().equals(command.getFavor().getId()) && f.getValue().getId().equals(command.getUser().getId())){
                command.getEvent().getFavors().remove(f.getKey());
                command.getFavor().setProgress(command.getProgress());
                command.getEvent().getFavors().put(command.getFavor(),command.getUser());
                eventService.updateEvent(command.getEvent());
                favorService.updateFavor(command.getFavor());
                break;
            }
        }
        return validateEventCommandHandler.handle(new ValidateEventCommand(command.getUser(), command.getEvent()));
    }
}
