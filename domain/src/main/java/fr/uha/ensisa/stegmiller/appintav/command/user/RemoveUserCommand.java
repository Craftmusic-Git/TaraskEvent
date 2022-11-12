package fr.uha.ensisa.stegmiller.appintav.command.user;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Voidy;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import lombok.Getter;

@Getter
public class RemoveUserCommand implements Command<Voidy> {
    User user;

    public RemoveUserCommand(User user){
        this.user = user;
    }
}
