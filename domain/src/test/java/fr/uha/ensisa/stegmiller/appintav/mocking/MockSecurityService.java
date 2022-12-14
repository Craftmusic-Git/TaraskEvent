package fr.uha.ensisa.stegmiller.appintav.mocking;

import fr.uha.ensisa.stegmiller.appintav.model.User;
import fr.uha.ensisa.stegmiller.appintav.service.SecurityService;
import lombok.Data;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.rmi.UnexpectedException;

@Profile("test")
@Data
@Service
public class MockSecurityService implements SecurityService {

    @Override
    public String registerUser(User user, String password) throws UnexpectedException {
        return "aze0iz-3fr9u-bfj45-23gy8-1n";
    }
}
