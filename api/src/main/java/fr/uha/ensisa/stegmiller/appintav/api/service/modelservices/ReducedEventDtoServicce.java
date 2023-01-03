package fr.uha.ensisa.stegmiller.appintav.api.service.modelservices;

import fr.uha.ensisa.stegmiller.appintav.api.dto.model.ReducedEventDto;
import fr.uha.ensisa.stegmiller.appintav.core.DTOServiceOfModel;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import fr.uha.ensisa.stegmiller.appintav.persistence.repositories.EventDAORepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ReducedEventDtoServicce implements DTOServiceOfModel<ReducedEventDto, Event> {

    final EventDAORepository eventDAO;

    public ReducedEventDtoServicce(EventDAORepository eventDAO) {
        this.eventDAO = eventDAO;
    }

    @Override
    public ReducedEventDto newInstanceOfDTO() {
        return new ReducedEventDto();
    }

    @Override
    public JpaRepository<Event, Long> getRepository() {
        return eventDAO;
    }

    public Iterable<ReducedEventDto> getAllEventOfAGuest(User guest) {
        return modelToDTOList(eventDAO.findByGuests_Id(guest.getId()));
    }
}
