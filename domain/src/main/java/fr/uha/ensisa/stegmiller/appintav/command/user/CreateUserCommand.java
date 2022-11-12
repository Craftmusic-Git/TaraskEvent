package fr.uha.ensisa.stegmiller.appintav.command.user;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import lombok.Getter;

@Getter
public class CreateUserCommand implements Command<User> {

    private User user;

    public CreateUserCommand(User user){
        this.user = user;
    }
}
