package fr.uha.ensisa.stegmiller.appintav.command.favor;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.Favor;
import fr.uha.ensisa.stegmiller.appintav.service.FavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class UpdateFavorPropertyCommandHandler implements Command.Handler<UpdateFavorPropertyCommand, Favor>{

    @Autowired
    private FavorService favorService;

    private static final Logger LOGGER = Logger.getLogger("UpdateFavorPropertyHandler");

    @Override
    public Favor handle(UpdateFavorPropertyCommand command) {
        if(command.getFavor() == null){
            LOGGER.warning("Favor is null");
            throw new Error("Favor is null");
        }
        if(command.getProperty() == null){
            LOGGER.warning("Property is null");
            throw new Error("Property is null");
        }
        if(command.getValue() == null){
            LOGGER.warning("Object is null");
            throw new Error("Object is null");
        }
        switch (command.getProperty()){
            case NAME -> command.getFavor().setTitle((String) command.getValue());
            case DESCRIPTION -> command.getFavor().setDescription((String) command.getValue());
            case PERCENTAGE -> command.getFavor().setProgress((int)command.getValue());
        }
        var rep = favorService.updateFavor(command.getFavor());
        LOGGER.info("Property : "+" was updated");
        return rep;
    }
}
