package fr.uha.ensisa.stegmiller.appintav.command.event;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import lombok.Getter;

@Getter
public class UpdateEventOrganisationCommand implements Command<Event> {

    public enum Property {
        DATE("date"),
        CAPACITY("capacité"),
        EXTERN("en exterieur"),
        LIMIT_AGE("age limite");

        private String propertyName;

        Property(String property) {
            propertyName = property;
        }

        public String getPropertyName() {
            return propertyName;
        }
    }

    private Property propertyType;
    private Event event;
    private User organisator;
    private Object information;

    public UpdateEventOrganisationCommand(Event event, User organisator, Property propertyType, Object information){
        this.event = event;
        this.organisator = organisator;
        this.propertyType = propertyType;
        this.information = information;
    }
}
