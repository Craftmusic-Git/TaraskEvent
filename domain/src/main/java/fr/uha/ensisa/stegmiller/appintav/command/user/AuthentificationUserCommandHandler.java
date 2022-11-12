package fr.uha.ensisa.stegmiller.appintav.command.user;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import fr.uha.ensisa.stegmiller.appintav.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class AuthentificationUserCommandHandler implements Command.Handler<AuthentificationUserCommand,User>{

    private static final Logger LOGGER =  Logger.getLogger("AuthentificationUserCommandHandler");

    @Autowired
    private UserService userService;

    @Override
    public User handle(AuthentificationUserCommand command) {
        if(command.getUser() == null)
            throw new Error("User is null");
        User user = command.getUser();
        var result = userService.searchUserByNameFirstName(user.getName(), user.getFirstname());
        if(result.isEmpty()) {
            LOGGER.info("A user try a connection");
            throw new Error("User not found !");
        }
        User rep = result.get(0);
        LOGGER.info(user + " is connected !");
        user.setStatus(User.Status.CONNECTED);
        rep.setStatus(User.Status.CONNECTED);
        return rep;
    }
}
