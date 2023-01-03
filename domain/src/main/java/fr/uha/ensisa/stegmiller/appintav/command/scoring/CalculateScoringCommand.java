package fr.uha.ensisa.stegmiller.appintav.command.scoring;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.Organisation;
import fr.uha.ensisa.stegmiller.appintav.model.Scoring;
import lombok.Getter;

@Getter
public class CalculateScoringCommand implements Command<Scoring> {
    private Organisation.Day day;
    private Organisation.Weather weather;
    private int temperature;
    private boolean isExterior;
    private boolean isDuringVacancy;

    public CalculateScoringCommand(Organisation.Day day, Organisation.Weather weather, int temperature, boolean isExterior, boolean isDuringVacancy){
        this.day = day;
        this.weather = weather;
        this.temperature = temperature;
        this.isDuringVacancy = isDuringVacancy;
        this.isExterior = isExterior;
    }
}
