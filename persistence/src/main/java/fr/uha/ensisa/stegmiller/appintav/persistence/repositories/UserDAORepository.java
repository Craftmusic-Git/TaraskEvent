package fr.uha.ensisa.stegmiller.appintav.persistence.repositories;

import fr.uha.ensisa.stegmiller.appintav.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDAORepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.name like concat('%', ?1, '%') and u.firstname like concat('%', ?2, '%')")
    List<User> findUsersByNameAndFirstname(String name, String firstname);

    Boolean existsUserByUsername(String username);

    Optional<User> findByUsername(String username);

    Optional<User> findByKeycloakId(String keycloakId);

}
