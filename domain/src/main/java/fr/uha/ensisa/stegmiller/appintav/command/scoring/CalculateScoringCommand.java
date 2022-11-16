package fr.uha.ensisa.stegmiller.appintav.command.scoring;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.Organization;
import fr.uha.ensisa.stegmiller.appintav.model.Scoring;
import lombok.Getter;

@Getter
public class CalculateScoringCommand implements Command<Scoring> {
    private Organization.Day day;
    private Organization.Weather weather;
    private int temperature;
    private boolean isExterior;
    private boolean isDuringVacancy;

    public CalculateScoringCommand(Organization.Day day, Organization.Weather weather, int temperature, boolean isExterior, boolean isDuringVacancy){
        this.day = day;
        this.weather = weather;
        this.temperature = temperature;
        this.isDuringVacancy = isDuringVacancy;
        this.isExterior = isExterior;
    }
}
