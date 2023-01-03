package fr.uha.ensisa.stegmiller.appintav.command.scoring;

import an.awesome.pipelinr.Command;
import fr.uha.ensisa.stegmiller.appintav.model.Organisation;
import fr.uha.ensisa.stegmiller.appintav.model.Scoring;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class CalculateScoringCommandHandler implements Command.Handler<CalculateScoringCommand, Scoring> {

    private static final Logger LOGGER = Logger.getLogger("CalculateScoringHandler");

    @Override
    public Scoring handle(CalculateScoringCommand command) {
        if(command.getDay() == null){
            LOGGER.warning("Day is null");
            throw new Error("Day is null");
        }
        if(command.getWeather() == null){
            LOGGER.warning("Weather is null");
            throw new Error("Weather is null");
        }
        Scoring rep = new Scoring();
        int date = 0;
        switch (command.getDay()){
            case MONDAY :
            case TUESDAY :
            case WEDNESDAY :
            case THURSDAY :
            case FRIDAY :
                break;
            case SATURDAY :
            case SUNDAY:
                date += 3;
                break;
        }
        if(command.isDuringVacancy())
            date += 2;
        rep.setDateScore(date);
        Organisation.Weather weather = command.getWeather();
        if(command.isExterior()) {
            if (weather == Organisation.Weather.RAINING || weather == Organisation.Weather.TEMPEST || weather == Organisation.Weather.SNOWING || command.getTemperature() < 10)
                rep.setWeatherScore(0);
            else if (command.getTemperature() > 30) {
                rep.setWeatherScore(3);
            } else rep.setWeatherScore(5);
        }else{
            if(weather == Organisation.Weather.SNOWING || weather == Organisation.Weather.TEMPEST || command.getTemperature() > 30)
                rep.setWeatherScore(3);
            else rep.setWeatherScore(5);
        }
        return rep;
    }
}
