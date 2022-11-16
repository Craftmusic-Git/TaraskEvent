package fr.uha.ensisa.stegmiller.appintav.model;

import fr.uha.ensisa.stegmiller.appintav.core.Model;
import fr.uha.ensisa.stegmiller.appintav.model.value.City;
import fr.uha.ensisa.stegmiller.appintav.model.value.CityCode;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "address")
public class Address extends Model<Address> {
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "code_id")
    private CityCode code;

    @Column(name = "number")
    private Integer number;

    @Column(name = "way")
    private String way;

    @Override
    public Address update(Address model) {
        super.update(model);
        if(model.getCode() != null)
            this.code = model.getCode();
        if(model.getCity() != null)
            this.city = model.getCity();
        if(model.getWay() != null)
            this.way = model.getWay();
        if(model.getNumber() != null)
            this.number = model.getNumber();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Address address = (Address) o;
        return id != null && Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
