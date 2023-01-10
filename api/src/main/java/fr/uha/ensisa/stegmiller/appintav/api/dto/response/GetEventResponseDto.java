package fr.uha.ensisa.stegmiller.appintav.api.dto.response;

import fr.uha.ensisa.stegmiller.appintav.api.dto.model.FavorDto;
import fr.uha.ensisa.stegmiller.appintav.api.dto.model.GuestDto;
import fr.uha.ensisa.stegmiller.appintav.api.dto.model.OrganisationDto;
import fr.uha.ensisa.stegmiller.appintav.core.DTO;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetEventResponseDto extends DTO {

    private String name;

    private Event.Statut statut;

    private OrganisationDto organisation;

    private List<GuestDto> guests;

    private List<FavorDto> emptyFavors;

    public GetEventResponseDto() {
        guests = new ArrayList<>();
        emptyFavors = new ArrayList<>();
    }
}
