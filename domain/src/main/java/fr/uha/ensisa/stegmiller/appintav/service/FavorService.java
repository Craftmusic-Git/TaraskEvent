package fr.uha.ensisa.stegmiller.appintav.service;

import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.Favor;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import org.springframework.stereotype.Service;

@Service
public interface FavorService {
    Favor createFavor(final Event event, final Favor favor);
    Favor addUserToFavor(final Favor favor, final User user);
    Favor updateFavor(final Favor favor);
}
