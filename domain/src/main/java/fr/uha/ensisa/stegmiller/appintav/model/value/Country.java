package fr.uha.ensisa.stegmiller.appintav.model.value;

import fr.uha.ensisa.stegmiller.appintav.core.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class Country extends Model {
    @Column(name = "name")
    private String name;

    public Country(){
        name = "";
    }

    public Country(String name){
        this.name = check(name);
    }

    private String check(String name){
        return name.trim().toUpperCase();
    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        return prime * result + ((name == null) ? 0 : name.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Country other = (Country) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}
