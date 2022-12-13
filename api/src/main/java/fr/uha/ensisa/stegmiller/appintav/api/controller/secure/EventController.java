package fr.uha.ensisa.stegmiller.appintav.api.controller.secure;

import fr.uha.ensisa.stegmiller.appintav.api.service.modelservices.ReducedEventDtoServicce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController()
@RequestMapping(value = "/api/auth/event")
public class EventController {

    @Autowired
    ReducedEventDtoServicce redEventService;


}
