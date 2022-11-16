package fr.uha.ensisa.stegmiller.appintav.command.link;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.Link;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import lombok.Getter;

@Getter
public class JoinEventByLinkCommand implements Command<Event> {
    private User user;
    private Link link;

    public JoinEventByLinkCommand(User user, Link link){
        this.user = user;
        this.link = link;
    }
}
