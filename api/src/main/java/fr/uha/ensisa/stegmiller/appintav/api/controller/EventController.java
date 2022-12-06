package fr.uha.ensisa.stegmiller.appintav.api.controller;

import fr.uha.ensisa.stegmiller.appintav.api.service.modelservices.ReducedEventDtoServicce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api/event")
public class EventController {

    @Autowired
    ReducedEventDtoServicce redEventService;


}
