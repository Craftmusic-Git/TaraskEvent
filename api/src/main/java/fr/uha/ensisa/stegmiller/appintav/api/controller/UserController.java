package fr.uha.ensisa.stegmiller.appintav.api.controller;

import fr.uha.ensisa.stegmiller.appintav.command.event.CreateEventCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    CreateEventCommandHandler createEventHandler;

    @GetMapping
    public String hello(){
        return "hello";
    }

}