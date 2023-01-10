package fr.uha.ensisa.stegmiller.appintav.api.dto.request;


import fr.uha.ensisa.stegmiller.appintav.command.event.UpdateEventOrganisationCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateEventRequestDto {
    private UpdateEventOrganisationCommand.Property property;

    private Object information;
}
