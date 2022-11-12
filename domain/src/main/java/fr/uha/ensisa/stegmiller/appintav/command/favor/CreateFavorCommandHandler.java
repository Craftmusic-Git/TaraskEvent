package fr.uha.ensisa.stegmiller.appintav.command.favor;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.Favor;
import fr.uha.ensisa.stegmiller.appintav.service.FavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class CreateFavorCommandHandler implements Command.Handler<CreateFavorCommand, Favor>{

    @Autowired
    FavorService favorService;

    private static final Logger LOGGER = Logger.getLogger("CreateFavorHandler");

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

        var f = favorService.createFavor(command.getEvent(),command.getFavor());
        return f;
    }
}
