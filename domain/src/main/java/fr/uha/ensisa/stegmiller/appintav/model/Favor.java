package fr.uha.ensisa.stegmiller.appintav.model;

import fr.uha.ensisa.stegmiller.appintav.core.Model;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "favors")
public class Favor extends Model<Favor> {
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "progress")
    private Integer progress;

    public Favor(String title, String description){
        this.title = title;
        this.description = description;
        progress = 0;
    }

    @Override
    public Favor update(Favor model) {
        super.update(model);
        if(model.getProgress() != null)
            this.progress = model.getProgress();
        if(model.getDescription() != null)
            this.description = model.getDescription();
        if(model.getTitle() != null)
            this.title = model.getTitle();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Favor favor = (Favor) o;
        return id != null && Objects.equals(id, favor.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
