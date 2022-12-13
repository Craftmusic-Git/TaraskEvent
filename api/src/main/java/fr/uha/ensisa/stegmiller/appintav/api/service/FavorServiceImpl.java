package fr.uha.ensisa.stegmiller.appintav.api.service;

import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.Favor;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import fr.uha.ensisa.stegmiller.appintav.persistence.repositories.EventDAORepository;
import fr.uha.ensisa.stegmiller.appintav.persistence.repositories.FavorDAORepository;
import fr.uha.ensisa.stegmiller.appintav.persistence.repositories.UserDAORepository;
import fr.uha.ensisa.stegmiller.appintav.service.FavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FavorServiceImpl implements FavorService {
    @Autowired
    FavorDAORepository favorDAO;

    @Autowired
    EventDAORepository eventDAO;

    @Autowired
    UserDAORepository userDAO;

    public FavorServiceImpl(){
    }

    @Override
    public Favor createFavor(Event event, Favor favor) {
        Favor rep = new Favor();
        rep.setDescription(favor.getDescription());
        rep.setProgress(favor.getProgress());
        rep.setTitle(favor.getTitle());
        Favor var = favorDAO.save(rep);
        event.getFavors().put(var,null);
        eventDAO.save(event);
        return var;
    }

    @Override
    public Favor addUserToFavor(Favor favor, User user) {
        user.getManagedFavor().add(favor);
        userDAO.save(user);
        return favor;
    }

    @Override
    public Favor updateFavor(Favor favor) {
        return favorDAO.save(favor);
    }
}
