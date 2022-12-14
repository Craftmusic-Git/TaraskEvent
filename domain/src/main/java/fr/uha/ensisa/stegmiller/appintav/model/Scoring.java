package fr.uha.ensisa.stegmiller.appintav.model;

public class Scoring {

    int weatherScore;
    int dateScore;
    int globalScore;

    public Scoring(){
        weatherScore = 0;
        dateScore = 0;
        globalScore = 0;
    }

    public int getWeatherScore() {
        return weatherScore;
    }

    public void setWeatherScore(int weatherScore) {
        this.weatherScore = weatherScore;
    }

    public int getDateScore() {
        return dateScore;
    }

    public void setDateScore(int dateScore) {
        this.dateScore = dateScore;
    }

    public int getGlobalScore() {
        globalScore = dateScore + weatherScore;
        return globalScore;
    }
}
