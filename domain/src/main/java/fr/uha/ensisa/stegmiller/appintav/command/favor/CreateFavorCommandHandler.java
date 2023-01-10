package fr.uha.ensisa.stegmiller.appintav.command.favor;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.Favor;
import fr.uha.ensisa.stegmiller.appintav.service.EventService;
import fr.uha.ensisa.stegmiller.appintav.service.FavorService;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class CreateFavorCommandHandler implements Command.Handler<CreateFavorCommand, Favor>{

    final FavorService favorService;

    final EventService eventService;

    private static final Logger LOGGER = Logger.getLogger("CreateFavorHandler");

    public CreateFavorCommandHandler(FavorService favorService, EventService eventService) {
        this.favorService = favorService;
        this.eventService = eventService;
    }

    @Override
    public Favor handle(CreateFavorCommand command) {
        if(command.getFavor() == null){
            LOGGER.warning("Favor is null");
            throw new Error("Favor is null");
        }
        if(command.getEvent() == null){
            LOGGER.warning("Event is null");
            throw new Error("Event is null");
        }

        if(command.getOrganizator() != null)
            LOGGER.info(command.getOrganizator() + " created a new favor");

        var f = favorService.createFavor(command.getEvent(),command.getFavor());
        return f;
    }
}
