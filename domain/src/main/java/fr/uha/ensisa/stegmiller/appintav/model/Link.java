package fr.uha.ensisa.stegmiller.appintav.model;

import fr.uha.ensisa.stegmiller.appintav.core.Model;
import lombok.Data;

@Data
public class Link extends Model {
    public static final int LINK_LENGTH = 5;

    String link;
    Event event;
}
