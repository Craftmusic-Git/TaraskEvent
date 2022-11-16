package fr.uha.ensisa.stegmiller.appintav.model;

import fr.uha.ensisa.stegmiller.appintav.core.Model;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "links")
public class Link extends Model<Link> {
    public static final int LINK_LENGTH = 5;

    @Column(name = "link")
    String link;

    @ManyToOne
    @JoinColumn(name = "event_id")
    Event event;

    @Override
    public Link update(Link model) {
        super.update(model);
        if(model.getLink() != null)
            this.link = model.getLink();
        if(model.getEvent() != null)
            this.event = model.getEvent();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Link link = (Link) o;
        return id != null && Objects.equals(id, link.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
