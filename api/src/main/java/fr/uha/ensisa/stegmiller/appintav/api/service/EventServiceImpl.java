package fr.uha.ensisa.stegmiller.appintav.api.service;

import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import fr.uha.ensisa.stegmiller.appintav.persistence.repositories.EventDAORepository;
import fr.uha.ensisa.stegmiller.appintav.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class EventServiceImpl implements EventService {
    @Autowired
    EventDAORepository eventDAO;

    public EventServiceImpl(){

    }

    @Override
    public Event createEvent(Event event) {
        event.setStatut(Event.Statut.CONFIGURATION);
        return eventDAO.save(event);
    }

    @Override
    public Event addEvent(Event event) {
        return eventDAO.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventDAO.save(event);
    }

    @Override
    public Optional<Event> getEventById(final Long id) {
        return eventDAO.findById(id);
    }

    @Override
    public List<Event> getEventOfSpecifiedUserID(Long id) {
        return eventDAO.findByGuests_Id(id);
    }

    @Override
    public void removeAnUserFromAllEvent(final User user) {
        List<Event> savedEvents = new ArrayList<>();
        for(var e : eventDAO.findAll()){
            if(e.getGuests().contains(user)){
                e.getGuests().remove(user);
                savedEvents.add(e);
            }
        }
        if(!savedEvents.isEmpty())
            eventDAO.saveAll(savedEvents);
    }

    @Override
    public Event addUserToEvent(Event event, User user) {
        event.getGuests().add(user);
        return eventDAO.save(event);
    }
}
