package fr.uha.ensisa.stegmiller.appintav.api.dto.response;

import fr.uha.ensisa.stegmiller.appintav.api.dto.model.ReducedEventDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEventsResponseDto {
    Iterable<ReducedEventDto> eventsOrganized;

    Iterable<ReducedEventDto> events;
}
