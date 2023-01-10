package fr.uha.ensisa.stegmiller.appintav.api.dto.request;

import fr.uha.ensisa.stegmiller.appintav.command.favor.UpdateFavorPropertyCommand;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdateFavorRequestDto {

    private UpdateFavorPropertyCommand.Property property;

    private Object information;
}
