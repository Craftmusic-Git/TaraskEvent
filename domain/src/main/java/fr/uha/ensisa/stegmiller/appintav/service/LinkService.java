package fr.uha.ensisa.stegmiller.appintav.service;

import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.Link;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface LinkService {
    Link createLink(Event event);
    Optional<Link> getLinkById(Long id);
    Optional<Link> getLinkByString(String string);
}
