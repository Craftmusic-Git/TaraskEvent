package fr.uha.ensisa.stegmiller.appintav.service;

import fr.uha.ensisa.stegmiller.appintav.model.User;
import org.springframework.stereotype.Service;

import java.rmi.UnexpectedException;

@Service
public interface SecurityService {
    String registerUser(User user, String password) throws UnexpectedException;
}
