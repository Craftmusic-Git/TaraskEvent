package fr.uha.ensisa.stegmiller.appintav.api.dto.request;

import fr.uha.ensisa.stegmiller.appintav.api.dto.model.UserRegistrationDto;
import lombok.Data;

@Data
public class RegisterRequestDto {
    UserRegistrationDto user;
}
