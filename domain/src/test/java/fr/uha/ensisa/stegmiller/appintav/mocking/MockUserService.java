package fr.uha.ensisa.stegmiller.appintav.mocking;

import fr.uha.ensisa.stegmiller.appintav.model.User;
import fr.uha.ensisa.stegmiller.appintav.service.UserService;
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
public class MockUserService implements UserService {

    private ArrayList<User> users = new ArrayList<>();
    private long counter;

    public MockUserService(){
        counter = 0L;
    }

    @Override
    public User createUser(final User user) {
        boolean isUnique = true;

        for(var u : users){
            if (Objects.equals(u.getId(), user.getId())) {
                isUnique = false;
                break;
            }
        }

        if(isUnique) {
            User rep = new User();
            rep.setStatus(user.getStatus());
            rep.setArchived(user.getArchived());
            rep.setName(user.getName());
            rep.setLastname(user.getLastname());
            rep.setBirthdate(user.getBirthdate());
            rep.setEventOrganized(user.getEventOrganized());
            rep.setManagedFavor(user.getManagedFavor());
            rep.setId(counter);
            counter++;
            users.add(rep);
            return rep;
        }

        return getUserById(user.getId()).get();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        User rep = null;
        for(var u : users)
            if(Objects.equals(u.getId(), id)){
                if(u.getArchived())
                    throw new Error(u + " is archived");
                rep = u;
                break;
            }
        return Optional.ofNullable(rep);
    }

    @Override
    public List<User> searchUserByNameLastName(String name, String lastName) {
        ArrayList<User> rep = new ArrayList<>();
        for(var u : users)
            if(Objects.equals(u.getName(), name) && Objects.equals(u.getLastname(), lastName))
                rep.add(u);
        return rep;
    }

    @Override
    public void removeUser(User user) {
        if(user.getArchived())
            return;
        for(var u : users)
            if(u.getId().equals(user.getId())) {
                user.setArchived(true);

            }
    }

    @Override
    public User updateUser(User user) {
        return user;
    }
}
