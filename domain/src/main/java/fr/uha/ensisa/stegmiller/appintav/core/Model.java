package fr.uha.ensisa.stegmiller.appintav.core;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Model<SELF extends Model<SELF>> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public SELF update(SELF model){
        if(model.getId() != null)
            this.id = model.getId();
        return (SELF) this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Model model = (Model) o;
        return id != null && Objects.equals(id, model.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
