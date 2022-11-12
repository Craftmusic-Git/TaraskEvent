package fr.uha.ensisa.stegmiller.appintav.command.user;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Voidy;
import fr.uha.ensisa.stegmiller.appintav.service.EventService;
import fr.uha.ensisa.stegmiller.appintav.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class RemoveUserCommandHandler implements Command.Handler<RemoveUserCommand, Voidy>{

    private static final Logger LOGGER = Logger.getLogger("RemoveUserHandler");

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Override
    public Voidy handle(RemoveUserCommand command) {
        if(command.getUser() == null)
            throw new Error("User is null");
        if(command.getUser().getArchived())
            throw new Error("User is already archived");
        eventService.removeAnUserFromAllEvent(command.getUser());
        userService.removeUser(command.getUser());
        LOGGER.info(command.getUser() + " is removed");
        return new Voidy();
    }
}
