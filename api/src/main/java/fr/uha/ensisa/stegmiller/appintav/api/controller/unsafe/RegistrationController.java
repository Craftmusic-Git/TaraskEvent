package fr.uha.ensisa.stegmiller.appintav.api.controller.unsafe;

import fr.uha.ensisa.stegmiller.appintav.api.dto.request.RegisterRequestDto;
import fr.uha.ensisa.stegmiller.appintav.api.dto.response.RegisterResponseDto;
import fr.uha.ensisa.stegmiller.appintav.api.service.modelservices.UserRegistrationDtoService;
import fr.uha.ensisa.stegmiller.appintav.command.user.CreateUserCommand;
import fr.uha.ensisa.stegmiller.appintav.command.user.CreateUserCommandHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.BadRequestException;

@RestController
@RequestMapping("/api/public/register")
public class RegistrationController {

    CreateUserCommandHandler createUserCommandHandler;

    UserRegistrationDtoService userRegistrationDtoService;

    RegistrationController(CreateUserCommandHandler createUserCommandHandler,
                           UserRegistrationDtoService userRegistrationDtoService) {
        this.createUserCommandHandler = createUserCommandHandler;
        this.userRegistrationDtoService = userRegistrationDtoService;
    }

    @PostMapping
    public RegisterResponseDto createUser(@RequestBody RegisterRequestDto request){
        if (request == null)
            throw new BadRequestException("Empty request");

        CreateUserCommand command = new CreateUserCommand(userRegistrationDtoService.dtoToModel(request.getUser()), request.getUser().getPassword());
        RegisterResponseDto response = new RegisterResponseDto();
        createUserCommandHandler.handle(command);
        return null;
    }
}
