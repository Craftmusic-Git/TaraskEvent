package fr.uha.ensisa.stegmiller.appintav.command.user;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import lombok.Getter;

@Getter
public class AuthentificationUserCommand implements Command<User> {
    private final User user;

    public AuthentificationUserCommand(User user){
        this.user = user;
    }
}
