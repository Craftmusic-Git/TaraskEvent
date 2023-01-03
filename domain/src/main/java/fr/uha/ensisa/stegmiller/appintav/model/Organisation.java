package fr.uha.ensisa.stegmiller.appintav.model;

import fr.uha.ensisa.stegmiller.appintav.core.Model;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "organizations")
public class Organisation extends Model<Organisation> {

    public enum Day{
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    public enum Weather {
        SUNNY,
        RAINING,
        SNOWING,
        TEMPEST
    }

    @Transient
    Day day;

    @Transient
    Weather weather;

    @Column(name = "date")
    private Date date;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "outside")
    private Boolean isOutside;

    @Column(name = "age_limit")
    private Integer ageLimit;

    @Transient
    private Scoring scoring;

    public Organisation(){
        date = null;
        capacity = 5;
        isOutside = Boolean.FALSE;
        ageLimit = 18;
        scoring = new Scoring();
    }

    public Organisation(Date date){
        this.date = date;
        capacity = 5;
        isOutside = Boolean.FALSE;
        ageLimit = 18;
        scoring = new Scoring();
    }

    @Override
    public Organisation update(Organisation model) {
        super.update(model);
        if(model.getIsOutside() != null)
            this.isOutside = model.getIsOutside();
        if(model.getDay() != null)
            this.day = model.getDay();
        if(model.getDate() != null)
            this.date = model.getDate();
        if(model.getAgeLimit() != null)
            this.ageLimit = model.getAgeLimit();
        if(model.getWeather() != null)
            this.weather = model.getWeather();
        if(model.getCapacity() != null)
            this.capacity = model.getCapacity();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Organisation that = (Organisation) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
