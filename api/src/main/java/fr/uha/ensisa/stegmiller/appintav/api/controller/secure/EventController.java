package fr.uha.ensisa.stegmiller.appintav.api.controller.secure;

import fr.uha.ensisa.stegmiller.appintav.api.dto.model.*;
import fr.uha.ensisa.stegmiller.appintav.api.dto.request.CreateEventLinkRequestDto;
import fr.uha.ensisa.stegmiller.appintav.api.dto.request.CreateEventRequestDto;
import fr.uha.ensisa.stegmiller.appintav.api.dto.request.UpdateEventRequestDto;
import fr.uha.ensisa.stegmiller.appintav.api.dto.response.CreateEventLinkResponseDto;
import fr.uha.ensisa.stegmiller.appintav.api.dto.response.GetEventResponseDto;
import fr.uha.ensisa.stegmiller.appintav.api.dto.response.UserEventsResponseDto;
import fr.uha.ensisa.stegmiller.appintav.api.service.EventServiceImpl;
import fr.uha.ensisa.stegmiller.appintav.api.service.UserServiceImpl;
import fr.uha.ensisa.stegmiller.appintav.api.service.modelservices.CreateEventDtoService;
import fr.uha.ensisa.stegmiller.appintav.api.service.modelservices.FavorDtoService;
import fr.uha.ensisa.stegmiller.appintav.api.service.modelservices.ReducedEventDtoServicce;
import fr.uha.ensisa.stegmiller.appintav.command.event.*;
import fr.uha.ensisa.stegmiller.appintav.command.link.CreateLinkCommand;
import fr.uha.ensisa.stegmiller.appintav.command.link.CreateLinkCommandHandler;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.Favor;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController()
@RequestMapping(value = "/api/auth/event")
public class EventController {

    final UserServiceImpl userService;
    final EventServiceImpl eventService;

    final ReducedEventDtoServicce redEventService;
    final CreateEventDtoService createEventDtoService;
    final FavorDtoService favorDtoService;

    final CreateEventCommandHandler createEventCommandHandler;
    final UpdateEventOrganisationCommandHandler updateEventOrganisationCommandHandler;
    final CreateLinkCommandHandler createLinkCommandHandler;
    final JoinEventCommandHandler joinEventCommandHandler;

    public EventController(UserServiceImpl userService, EventServiceImpl eventService, ReducedEventDtoServicce redEventService, CreateEventDtoService createEventDtoService, FavorDtoService favorDtoService, CreateEventCommandHandler createEventCommandHandler, UpdateEventOrganisationCommandHandler updateEventOrganisationCommandHandler, CreateLinkCommandHandler createLinkCommandHandler, JoinEventCommandHandler joinEventCommandHandler) {
        this.userService = userService;
        this.eventService = eventService;
        this.redEventService = redEventService;
        this.createEventDtoService = createEventDtoService;
        this.favorDtoService = favorDtoService;
        this.createEventCommandHandler = createEventCommandHandler;
        this.updateEventOrganisationCommandHandler = updateEventOrganisationCommandHandler;
        this.createLinkCommandHandler = createLinkCommandHandler;
        this.joinEventCommandHandler = joinEventCommandHandler;
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

    @GetMapping
    public GetEventResponseDto getEventById(@RequestParam("id") final long id) {
        Optional<User> user = userService.getCurrentUser();

        if (user.isEmpty()) {
            throw new Error("No user !");
        }
        User currentUser = user.get();

        Optional<Event> event = eventService.getEventById(id);
        if (event.isEmpty()) {
            throw new Error("No event !");
        }
        Event currentEvent = event.get();

        if(currentUser.getEventOrganized().stream().noneMatch(e -> e.getId().equals(id)) && currentEvent.getGuests().stream().noneMatch(u -> u.getId().equals(currentUser.getId()))) {
            throw new Error("User is not in this event");
        }

        GetEventResponseDto response = new GetEventResponseDto();

        response.setName(currentEvent.getName());
        response.setId(id);
        response.setStatut(currentEvent.getStatut());

        OrganisationDto organisation = new OrganisationDto();
        organisation.dtoOfModel(currentEvent.getOrganisation());
        response.setOrganisation(organisation);

        Map<User, List<Favor>> usersFavors = getUsersFavorFromEvent(currentEvent);

        currentEvent.getGuests().forEach(g -> {
            GuestDto guest = new GuestDto();
            guest.dtoOfModel(g);
            if (usersFavors.containsKey(g)) {
                guest.setFavors(usersFavors.get(g).stream().map(FavorDto::new).toList());
            }
            response.getGuests().add(guest);
        });

        response.setEmptyFavors((List<FavorDto>) favorDtoService.modelToDTOList(currentEvent.getEmptyFavors()));

        return response;
    }

    @PatchMapping
    public GetEventResponseDto updateEventOrganisation(@RequestParam("id") final long id,@RequestBody final UpdateEventRequestDto request) {
        Optional<User> user = userService.getCurrentUser();

        if (user.isEmpty()) {
            throw new Error("No user !");
        }
        User currentUser = user.get();

        Optional<Event> event = eventService.getEventById(id);
        if (event.isEmpty()) {
            throw new Error("No event !");
        }
        Event currentEvent = event.get();

        if (currentUser.getEventOrganized().stream().noneMatch(e -> e.getId().equals(currentEvent.getId()))) {
            throw new Error("User si not the creator of the event");
        }

        UpdateEventOrganisationCommand command = new UpdateEventOrganisationCommand(currentEvent, currentUser, request.getProperty(), request.getInformation());
        updateEventOrganisationCommandHandler.handle(command);

        return getEventById(id);
    }

    @PostMapping("/link")
    public CreateEventLinkResponseDto CreateLink(@RequestBody final CreateEventLinkRequestDto request) {
        Optional<User> user = userService.getCurrentUser();

        if (user.isEmpty()) {
            throw new Error("No user !");
        }
        User currentUser = user.get();

        if (request.getEventID() == null) {
            throw new Error("No event id !");
        }

        Optional<Event> opEvent = eventService.getEventById(request.getEventID());
        if (opEvent.isEmpty()) {
            throw new Error("Event id is not found");
        }

        CreateEventLinkResponseDto rep = new CreateEventLinkResponseDto();
        rep.setLink(createLinkCommandHandler.handle(new CreateLinkCommand(opEvent.get())));
        return rep;
    }

    @PostMapping("/join")
    public GetEventResponseDto joinEvent(@RequestParam("id") Long id) {
        Optional<User> user = userService.getCurrentUser();

        if (user.isEmpty()) {
            throw new Error("No user !");
        }
        User currentUser = user.get();

        Optional<Event> opEvent = eventService.getEventById(id);

        if (opEvent.isEmpty()) {
            throw new Error("Event id not available");
        }

        Event currentEvent = opEvent.get();

        joinEventCommandHandler.handle(new JoinEventCommand(currentEvent, currentUser));

        GetEventResponseDto response = new GetEventResponseDto();

        response.setName(currentEvent.getName());
        response.setId(currentEvent.getId());
        response.setStatut(currentEvent.getStatut());

        OrganisationDto organisation = new OrganisationDto();
        organisation.dtoOfModel(currentEvent.getOrganisation());
        response.setOrganisation(organisation);

        Map<User, List<Favor>> usersFavors = getUsersFavorFromEvent(currentEvent);

        currentEvent.getGuests().forEach(g -> {
            GuestDto guest = new GuestDto();
            guest.dtoOfModel(g);
            if (usersFavors.containsKey(g)) {
                guest.setFavors(usersFavors.get(g).stream().map(FavorDto::new).toList());
            }
            response.getGuests().add(guest);
        });

        response.setEmptyFavors((List<FavorDto>) favorDtoService.modelToDTOList(currentEvent.getEmptyFavors()));

        return response;
    }

    private Map<User, List<Favor>> getUsersFavorFromEvent(Event event) {
        Map<User, List<Favor>> rep = new HashMap<>();
        event.getFavors().forEach((key, value) -> {
            if (!rep.containsKey(value)) {
                rep.put(value, new ArrayList<>());
            }
            rep.get(value).add(key);
        });
        return rep;
    }

    private List<Favor> getFavorWithoutManager(Event event) {
        List<Favor> favors = new ArrayList<>();

        event.getFavors().forEach((key, value) -> {
            if (value == null) favors.add(key);
        });

        return favors;
    }
}
