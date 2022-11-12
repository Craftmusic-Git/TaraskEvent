package fr.uha.ensisa.stegmiller.appintav.service;

import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EventService {
    Event addEvent(Event event);
    Optional<Event> getEventById(final Long id);
    List<Event> getEventOfSpecifiedUserID(final Long id);
    void removeAnUserFromAllEvent(final User user);
    Event addUserToEvent(final Event event, final User user);
}
