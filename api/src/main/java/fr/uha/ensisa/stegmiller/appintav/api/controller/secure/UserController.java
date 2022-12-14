package fr.uha.ensisa.stegmiller.appintav.api.controller.secure;

import fr.uha.ensisa.stegmiller.appintav.api.dto.model.UserDto;
import fr.uha.ensisa.stegmiller.appintav.api.service.modelservices.UserDtoService;
import fr.uha.ensisa.stegmiller.appintav.command.user.CreateUserCommand;
import fr.uha.ensisa.stegmiller.appintav.command.user.CreateUserCommandHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RestController()
@RequestMapping("/api/auth/user")
public class UserController {

    @Autowired
    UserDtoService userDtoService;

    @Autowired
    CreateUserCommandHandler createUserCommandHandler;

    @GetMapping(value = "/all")
    public Iterable<UserDto> getAllUser(){
        return userDtoService.getAll();
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto user){
        return userDtoService.modelToDTO(createUserCommandHandler.handle(new CreateUserCommand(userDtoService.dtoToModel(user),"azerty")));
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        userDtoService.removeById(userId);
    }

    @GetMapping("exemple")
    public UserDto sendExemple(){
        UserDto dto = new UserDto();
        dto.setName("Kenobi");
        dto.setLastname("Obi-wan");
        dto.setBirthdate(new Date(System.currentTimeMillis()));
        return dto;
    }
}