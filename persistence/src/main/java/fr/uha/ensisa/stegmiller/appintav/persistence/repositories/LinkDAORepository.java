package fr.uha.ensisa.stegmiller.appintav.persistence.repositories;

import fr.uha.ensisa.stegmiller.appintav.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinkDAORepository extends JpaRepository<Link, Long> {
    Optional<Link> findByLinkContains(String link);

}
