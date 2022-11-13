package fr.uha.ensisa.stegmiller.appintav.command.favor;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.Favor;
import lombok.Getter;

@Getter
public class UpdateFavorPropertyCommand implements Command<Favor> {

    public enum Property{
        NAME,
        DESCRIPTION,
        PERCENTAGE
    }

    private Property property;
    private Favor favor;
    private Object value;

    public UpdateFavorPropertyCommand(Property property, Object value, Favor favor){
        this.property = property;
        this.value = value;
        this.favor = favor;
    }
}
