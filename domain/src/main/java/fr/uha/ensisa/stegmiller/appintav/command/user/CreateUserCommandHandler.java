package fr.uha.ensisa.stegmiller.appintav.command.user;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import fr.uha.ensisa.stegmiller.appintav.service.SecurityService;
import fr.uha.ensisa.stegmiller.appintav.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.rmi.UnexpectedException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Logger;

@Component
public class CreateUserCommandHandler implements Command.Handler<CreateUserCommand, User> {

    private static final Logger LOGGER = Logger.getLogger("CreateUserCommandHandler");

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @Override
    public User handle(CreateUserCommand command) {
        if (command.getUser() == null)
            throw new Error("user not defined");
        if (command.getPassword() == null)
            throw new Error("Password not defined");

        User user = command.getUser();
        if (Objects.equals(user.getName(), "")) {
            LOGGER.warning("An user try to create new account without name");
            throw new Error("user have a empty string name");
        }
        if (Objects.equals(user.getLastname(), "")) {
            LOGGER.warning("An user try to create new account without firstname");
            throw new Error("user have a empty string firstname");
        }
        if (Objects.equals(command.getPassword(), "")) {
            LOGGER.warning("An user try to create new account without password");
            throw new Error("user have a empty string password");
        }

        Calendar c = Calendar.getInstance();
        c.setTime(Date.from(Instant.now()));
        c.add(Calendar.YEAR,-13);

        if (!user.getBirthdate().before(c.getTime())) {
            LOGGER.warning("User "+user.getName()+" is to young "+user.getBirthdate());
            throw new Error("user is to young " + user.getBirthdate());
        }
        if (user.getEventOrganized() == null) {
            user.setEventOrganized(new ArrayList<>());
        }
        if (user.getManagedFavor() == null) {
            user.setManagedFavor(new ArrayList<>());
        }

        try {
            user.setKeycloakId(securityService.registerUser(user, command.getPassword()));
        } catch (UnexpectedException e) {
            LOGGER.warning("The security registration process fail");
            throw new Error("The security registration process fail");
        }

        user.setStatus(User.Status.CONNECTED);
        User userServiceRep = userService.createUser(user);
        LOGGER.info(userServiceRep + " is created");
        return userServiceRep;
    }
}
