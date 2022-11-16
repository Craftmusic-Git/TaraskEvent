package fr.uha.ensisa.stegmiller.appintav.api.service;

import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.Link;
import fr.uha.ensisa.stegmiller.appintav.service.LinkService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import static fr.uha.ensisa.stegmiller.appintav.model.Link.LINK_LENGTH;

@Service
public class LinkServiceImpl implements LinkService {

    private ArrayList<Link> links = new ArrayList<>();

    private long count;

    public LinkServiceImpl(){
        count = 0L;
    }

    @Override
    public Link createLink(Event event) {
        Link rep = new Link();
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = LINK_LENGTH;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        rep.setLink(generatedString);
        rep.setEvent(event);
        rep.setId(count);
        count++;
        links.add(rep);
        return rep;
    }

    @Override
    public Optional<Link> getLinkById(Long id) {
        Link rep = null;
        for(var l : links)
            if (Objects.equals(l.getId(), id)) {
                rep = l;
                break;
            }
        return Optional.ofNullable(rep);
    }

    @Override
    public Optional<Link> getLinkByString(String string) {
        Link rep = null;
        for(var l : links)
            if (Objects.equals(l.getLink(), string)) {
                rep = l;
                break;
            }
        return Optional.ofNullable(rep);
    }
}