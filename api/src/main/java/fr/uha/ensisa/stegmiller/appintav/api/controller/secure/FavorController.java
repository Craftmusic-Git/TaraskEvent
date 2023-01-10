package fr.uha.ensisa.stegmiller.appintav.api.controller.secure;

import fr.uha.ensisa.stegmiller.appintav.api.dto.model.FavorDto;
import fr.uha.ensisa.stegmiller.appintav.api.dto.request.CreateFavorRequestDto;
import fr.uha.ensisa.stegmiller.appintav.api.dto.request.UpdateFavorRequestDto;
import fr.uha.ensisa.stegmiller.appintav.api.service.EventServiceImpl;
import fr.uha.ensisa.stegmiller.appintav.api.service.FavorServiceImpl;
import fr.uha.ensisa.stegmiller.appintav.api.service.UserServiceImpl;
import fr.uha.ensisa.stegmiller.appintav.api.service.modelservices.FavorDtoService;
import fr.uha.ensisa.stegmiller.appintav.command.favor.*;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import fr.uha.ensisa.stegmiller.appintav.model.Favor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController()
@RequestMapping(value = "/api/auth/favor")
public class FavorController {

    final UserServiceImpl userService;
    final EventServiceImpl eventService;
    final FavorServiceImpl favorService;

    final FavorDtoService favorDtoService;

    final CreateFavorCommandHandler createFavorCommandHandler;
    final FavorTakenByUserCommandHandler favorTakenByUserCommandHandler;
    final UpdateFavorPropertyCommandHandler updateFavorPropertyCommandHandler;

    public FavorController(UserServiceImpl userService, EventServiceImpl eventService, FavorServiceImpl favorService, FavorDtoService favorDtoService, CreateFavorCommandHandler createFavorCommandHandler, FavorTakenByUserCommandHandler favorTakenByUserCommandHandler, UpdateFavorPropertyCommandHandler updateFavorPropertyCommandHandler) {
        this.userService = userService;
        this.eventService = eventService;
        this.favorService = favorService;
        this.favorDtoService = favorDtoService;
        this.createFavorCommandHandler = createFavorCommandHandler;
        this.favorTakenByUserCommandHandler = favorTakenByUserCommandHandler;
        this.updateFavorPropertyCommandHandler = updateFavorPropertyCommandHandler;
    }

    @PostMapping
    public FavorDto createFavor(@RequestBody CreateFavorRequestDto request) {

        Optional<User> user = userService.getCurrentUser();

        if (user.isEmpty()) {
            throw new Error("No user !");
        }
        User currentUser = user.get();

        Optional<Event> event = eventService.getEventById(request.getEventId());
        if (event.isEmpty()) {
            throw new Error("No event !");
        }
        Event currentEvent = event.get();

        CreateFavorCommand command = new CreateFavorCommand(currentEvent, request.getFavor().modelOfDTO(), currentUser);

        return favorDtoService.modelToDTO(createFavorCommandHandler.handle(command));
    }

    @DeleteMapping
    public void deleteFavor(@RequestParam final Long id) {
        eventService.deleteEmptyFavorIdFromEvent(id);
    }

    @PatchMapping
    public void updateFavor(@RequestParam("id") final long id, @RequestBody final UpdateFavorRequestDto request) {
        Optional<User> user = userService.getCurrentUser();

        if (user.isEmpty()) {
            throw new Error("No user !");
        }
        User currentUser = user.get();

        Optional<Favor> favor = favorService.getFavorById(id);

        if (favor.isPresent()) {
            UpdateFavorPropertyCommand command = new UpdateFavorPropertyCommand(request.getProperty(), request.getInformation(), favor.get());
            updateFavorPropertyCommandHandler.handle(command);
        }
    }
}
