package fr.uha.ensisa.stegmiller.appintav.mocking;

import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import fr.uha.ensisa.stegmiller.appintav.service.EventService;
import lombok.Data;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Profile("test")
@Data
public class MockEventService implements EventService {
    private ArrayList<Event> events = new ArrayList<>();
    private Long counter;

    public MockEventService(){
        counter = 0L;
    }

    @Override
    public Event createEvent(Event event) {
        Event rep = addEvent(event);
        rep.setStatut(Event.Statut.CONFIGURATION);
        return rep;
    }

    @Override
    public Event addEvent(Event event) {
        boolean isUnique = true;

        for(var e : events)
            if(Objects.equals(e.getId(), event.getId()))
                isUnique = false;

        if(isUnique){
            Event rep = new Event();
            rep.setName(event.getName());
            rep.setGuests(event.getGuests());
            rep.setStatut(event.getStatut());
            rep.setLocationAddress(event.getLocationAddress());
            rep.setFavors(event.getFavors());
            rep.setOrganisation(event.getOrganisation());
            rep.setId(counter);
            counter++;
            events.add(rep);
            return rep;
        }

        return getEventById(event.getId()).get();
    }

    @Override
    public Event updateEvent(Event event) {
        for (var e: events) {
            if (Objects.equals(e.getId(), event.getId())) {
                e = event;
            }
        }
        return event;
    }

    @Override
    public Optional<Event> getEventById(final Long id) {
        Event rep = null;
        for(var e : events){
            if(Objects.equals(e.getId(), id)){
                rep = e;
                break;
            }
        }
        return Optional.ofNullable(rep);
    }

    @Override
    public List<Event> getEventOfSpecifiedUserID(Long id) {
        ArrayList<Event> rep = new ArrayList<>();
        for(var e : events){
            for(var u : e.getGuests()){
                if(u.getId().equals(id))
                    rep.add(e);
            }
        }
        return rep;
    }

    @Override
    public void removeAnUserFromAllEvent(final User user) {
        for(var e : events){
            if(e.getGuests().contains(user)){
                e.getGuests().remove(user);
            }
        }
    }

    @Override
    public Event addUserToEvent(Event event, User user) {
        for(var e : user.getEventOrganized()){
            if(Objects.equals(e.getId(), event.getId())){
                return event;
            }
        }
        for(var u : event.getGuests()){
            if(Objects.equals(u.getId(), user.getId()))
                return event;
        }
        event.getGuests().add(user);
        return event;
    }
}
