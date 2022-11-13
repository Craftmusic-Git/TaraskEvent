package fr.uha.ensisa.stegmiller.appintav.model;

import fr.uha.ensisa.stegmiller.appintav.core.Model;
import lombok.Data;

@Data
public class Favor extends Model {
    private String title;
    private String description;
    private int progress;

    public Favor(){
        title = "";
        description = "";
        progress = 0;
    }

    public Favor(String title, String description){
        this.title = title;
        this.description = description;
        progress = 0;
    }
}
