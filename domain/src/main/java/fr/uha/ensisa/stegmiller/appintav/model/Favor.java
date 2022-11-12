package fr.uha.ensisa.stegmiller.appintav.model;

import lombok.Data;

@Data
public class Favor {
    private Long id;
    private String title;
    private String description;
    private Float progress;

    public Favor(){
        title = "";
        description = "";
        progress = 0f;
    }

    public Favor(String title, String description){
        this.title = title;
        this.description = description;
        progress = 0f;
    }
}
