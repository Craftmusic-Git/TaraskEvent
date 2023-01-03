package fr.uha.ensisa.stegmiller.appintav.api.dto.request;

import fr.uha.ensisa.stegmiller.appintav.api.dto.model.CreationEventDto;
import lombok.Data;

@Data
public class CreateEventRequestDto {

    private CreationEventDto event;
}
