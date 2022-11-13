package fr.uha.ensisa.stegmiller.appintav.api.service;

import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.Favor;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import fr.uha.ensisa.stegmiller.appintav.service.FavorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FavorServiceImpl implements FavorService {
    private ArrayList<Favor> favors;
    private Long counts;

    public FavorServiceImpl(){
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

    @Override
    public Favor updateFavor(Favor favor) {
        for(var f : favors)
            if(f.getId().equals(favor.getId())) {
                f = favor;
                return f;
            }
        return favor;
    }
}
