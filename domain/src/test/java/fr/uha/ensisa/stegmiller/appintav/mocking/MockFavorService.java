package fr.uha.ensisa.stegmiller.appintav.mocking;

import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.Favor;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import fr.uha.ensisa.stegmiller.appintav.service.FavorService;
import lombok.Data;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Profile("test")
@Data
public class MockFavorService implements FavorService {
    private ArrayList<Favor> favors;
    private Long counts;

    public MockFavorService(){
        counts = 0L;
        favors = new ArrayList<>();
    }

    @Override
    public Favor createFavor(Event event, Favor favor) {
        Favor rep = new Favor();
        rep.setDescription(favor.getDescription());
        rep.setProgress(favor.getProgress());
        rep.setTitle(favor.getTitle());
        rep.setId(counts);
        counts++;
        favors.add(rep);
        event.getFavors().put(rep,null);
        return rep;
    }

    @Override
    public Favor addUserToFavor(Favor favor, User user) {
        boolean isContain = false;
        user.getManagedFavor().add(favor);
        return favor;
    }
}
