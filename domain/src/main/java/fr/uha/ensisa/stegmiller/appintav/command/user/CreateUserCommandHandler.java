package fr.uha.ensisa.stegmiller.appintav.command.user;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import fr.uha.ensisa.stegmiller.appintav.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

@Component
public class CreateUserCommandHandler implements Command.Handler<CreateUserCommand, User> {

    private static final Logger LOGGER = Logger.getLogger("CreateUserCommandHandler");

    @Autowired
    private UserService userService;

    @Override
    public User handle(CreateUserCommand command) {
        if(command.getUser() == null)
            throw new Error("user not defined");

        User user = command.getUser();
        if(user.getName() == "") {
            LOGGER.warning("An user try to create new account without name");
            throw new Error("user have a empty string name");
        }
        if(user.getFirstname() == "") {
            LOGGER.warning("An user try to create new account without firstname");
            throw new Error("user have a empty string firstname");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(Date.from(Instant.now()));
        c.add(Calendar.YEAR,-13);
        if(!user.getBirthdate().before(c.getTime())) {
            LOGGER.warning("User "+user.getName()+" is to young "+user.getBirthdate());
            throw new Error("user is to young " + user.getBirthdate());
        }
        user.setStatus(User.Status.CONNECTED);
        User userServiceRep = userService.createUser(user);
        LOGGER.info(userServiceRep + " is created");
        return userServiceRep;
    }
}
