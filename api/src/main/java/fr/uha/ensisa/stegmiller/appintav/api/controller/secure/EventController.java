package fr.uha.ensisa.stegmiller.appintav.api.controller.secure;

import fr.uha.ensisa.stegmiller.appintav.api.dto.model.CreationEventDto;
import fr.uha.ensisa.stegmiller.appintav.api.dto.model.ReducedEventDto;
import fr.uha.ensisa.stegmiller.appintav.api.dto.request.CreateEventRequestDto;
import fr.uha.ensisa.stegmiller.appintav.api.dto.response.UserEventsResponseDto;
import fr.uha.ensisa.stegmiller.appintav.api.service.UserServiceImpl;
import fr.uha.ensisa.stegmiller.appintav.api.service.modelservices.CreateEventDtoService;
import fr.uha.ensisa.stegmiller.appintav.api.service.modelservices.ReducedEventDtoServicce;
import fr.uha.ensisa.stegmiller.appintav.command.event.CreateEventCommand;
import fr.uha.ensisa.stegmiller.appintav.command.event.CreateEventCommandHandler;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController()
@RequestMapping(value = "/api/auth/event")
public class EventController {

    final UserServiceImpl userService;

    final ReducedEventDtoServicce redEventService;
    final CreateEventDtoService createEventDtoService;

    final CreateEventCommandHandler createEventCommandHandler;

    public EventController(UserServiceImpl userService, ReducedEventDtoServicce redEventService, CreateEventDtoService createEventDtoService, CreateEventCommandHandler createEventCommandHandler) {
        this.userService = userService;
        this.redEventService = redEventService;
        this.createEventDtoService = createEventDtoService;
        this.createEventCommandHandler = createEventCommandHandler;
    }

    @GetMapping("/all")
    public Iterable<ReducedEventDto> getAllEvents() {
        return redEventService.getAll();
    }

    @PostMapping
    public CreationEventDto createEvent(@RequestBody final CreateEventRequestDto request) {
        Optional<User> user = userService.getCurrentUser();

        CreateEventCommand command = new CreateEventCommand(user.get(), createEventDtoService.dtoToModel(request.getEvent()));
        return createEventDtoService.modelToDTO(createEventCommandHandler.handle(command));
    }

    @GetMapping("/me")
    public UserEventsResponseDto getUserEvent() {
        Optional<User> user = userService.getCurrentUser();

        if (!user.isPresent()) {
            throw new Error("No user !");
        }

        UserEventsResponseDto response = new UserEventsResponseDto();
        response.setEventsOrganized(redEventService.modelToDTOList(user.get().getEventOrganized()));
        response.setEvents(redEventService.getAllEventOfAGuest(user.get()));
        return response;
    }
}
