package fr.uha.ensisa.stegmiller.appintav.api.service;

import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.Link;
import fr.uha.ensisa.stegmiller.appintav.persistence.repositories.LinkDAORepository;
import fr.uha.ensisa.stegmiller.appintav.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

import static fr.uha.ensisa.stegmiller.appintav.model.Link.LINK_LENGTH;

@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    LinkDAORepository linkDAO;

    public LinkServiceImpl(){

    }

    @Override
    public Link createLink(Event event) {
        Link rep = new Link();
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(LINK_LENGTH)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        rep.setLink(generatedString);
        rep.setEvent(event);

        return linkDAO.save(rep);
    }

    @Override
    public Optional<Link> getLinkById(Long id) {
        return linkDAO.findById(id);
    }

    @Override
    public Optional<Link> getLinkByString(String string) {
        return linkDAO.findByLinkContains(string);
    }
}