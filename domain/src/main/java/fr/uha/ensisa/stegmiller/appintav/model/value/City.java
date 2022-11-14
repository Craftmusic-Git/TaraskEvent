package fr.uha.ensisa.stegmiller.appintav.model.value;

import fr.uha.ensisa.stegmiller.appintav.core.Model;

public class City extends Model {
    private String name;

    public City(String value){
        name = check(value);
    }

    private String check(String value){
        return value.trim().toUpperCase();
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
        City other = (City) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}
