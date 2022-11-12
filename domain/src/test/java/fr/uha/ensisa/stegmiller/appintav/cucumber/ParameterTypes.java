package fr.uha.ensisa.stegmiller.appintav.cucumber;

import fr.uha.ensisa.stegmiller.appintav.model.Event.Statut;
import io.cucumber.java.ParameterType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParameterTypes {
    @ParameterType("\\d{2}\\/\\d{2}\\/\\d{4}")
    public Date date(String dateString) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
    }

    @ParameterType("true|True|TRUE|false|False|FALSE|oui|Oui|OUI|non|Non|NON|vrai|Vrai|VRAI|faux|Faux|FAUX")
    public Boolean bool(String value) {
        switch (value.toLowerCase()) {
            case "true":
            case "oui":
            case "vrai":
                return true;
            default:
                return false;
        }
    }

    @ParameterType("Organisation|En attente|En cours|Prêt")
    public Statut statut(String value){
        switch (value){
            case "Organisation":
                return Statut.CONFIGURATION;
            case "En attente":
                return Statut.WAITING;
            case "En cours":
                return Statut.IN_PROGRESS;
            case "Prêt":
                return Statut.READY;
            default :
                return Statut.ERROR;
        }
    }
}
