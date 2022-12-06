package fr.uha.ensisa.stegmiller.appintav.api.controller;

import fr.uha.ensisa.stegmiller.appintav.api.dto.UserDto;
import fr.uha.ensisa.stegmiller.appintav.api.service.modelservices.UserDtoService;
import fr.uha.ensisa.stegmiller.appintav.command.user.CreateUserCommand;
import fr.uha.ensisa.stegmiller.appintav.command.user.CreateUserCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserDtoService userDtoService;

    @Autowired
    CreateUserCommandHandler createUserCommandHandler;

    @GetMapping(value = "all")
    public Iterable<UserDto> getAllUser(){
        return userDtoService.getAll();
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto user){
        return userDtoService.modelToDTO(createUserCommandHandler.handle(new CreateUserCommand(userDtoService.dtoToModel(user))));
    }

    @GetMapping("date")
    public Date sendLocalDate(){
        return new Date(System.currentTimeMillis());
    }

    @GetMapping("exemple")
    public UserDto sendExemple(){
        UserDto dto = new UserDto();
        dto.setName("Kenobi");
        dto.setFirstname("Obi-wan");
        dto.setBirthdate(new Date(System.currentTimeMillis()));
        return dto;
    }
}