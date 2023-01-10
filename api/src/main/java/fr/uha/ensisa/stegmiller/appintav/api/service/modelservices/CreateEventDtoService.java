package fr.uha.ensisa.stegmiller.appintav.api.service.modelservices;

import fr.uha.ensisa.stegmiller.appintav.api.dto.model.CreationEventDto;
import fr.uha.ensisa.stegmiller.appintav.core.DTOServiceOfModel;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.persistence.repositories.EventDAORepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateEventDtoService implements DTOServiceOfModel<CreationEventDto, Event> {

    private final EventDAORepository eventDAORepository;

    public CreateEventDtoService(EventDAORepository eventDAORepository) {
        this.eventDAORepository = eventDAORepository;
    }

    @Override
    public CreationEventDto newInstanceOfDTO() {
        return new CreationEventDto();
    }

    @Override
    public JpaRepository<Event, Long> getRepository() {
        return eventDAORepository;
    }
}
