package fr.uha.ensisa.stegmiller.appintav.api.dto.request;

import fr.uha.ensisa.stegmiller.appintav.api.dto.model.FavorDto;
import lombok.Data;

@Data
public class CreateFavorRequestDto {
    private Long eventId;
    private FavorDto favor;
}
