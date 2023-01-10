package fr.uha.ensisa.stegmiller.appintav.persistence.repositories;

import fr.uha.ensisa.stegmiller.appintav.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventDAORepository extends JpaRepository<Event, Long> {
    Optional<Event> findByEmptyFavors_Id(Long id);

    List<Event> findByGuests_Id(Long id);


}
