package fr.uha.ensisa.stegmiller.appintav.cucumber;

import fr.uha.ensisa.stegmiller.appintav.model.Event.Statut;
import fr.uha.ensisa.stegmiller.appintav.model.Organization;
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

    @ParameterType("En attente|En cours|Prêt")
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

    public static Statut sStatut(String value){
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

    @ParameterType("Lundi|Mardi|Mercredi|Jeudi|Vendredi|Samedi|Dimanche")
    public Organization.Day day(String value){
        switch (value){
            case "Lundi" -> { return Organization.Day.MONDAY; }
            case "Mardi" -> { return Organization.Day.TUESDAY; }
            case "Mercredi" -> { return Organization.Day.WEDNESDAY; }
            case "Jeudi" -> { return Organization.Day.THURSDAY; }
            case "Vendredi" -> { return Organization.Day.FRIDAY; }
            case "Samedi" -> { return Organization.Day.SATURDAY; }
            case "Dimanche" -> { return Organization.Day.SUNDAY; }
            default -> { return null; }
        }
    }

    @ParameterType("soleil|neige|tempête|pluie")
    public Organization.Weather weather(String value){
        switch (value){
            case "soleil" -> { return Organization.Weather.SUNNY; }
            case "neige" -> { return Organization.Weather.SNOWING; }
            case "tempête" -> { return Organization.Weather.TEMPEST; }
            case "pluie" -> { return Organization.Weather.RAINING; }
            default -> { return null; }
        }
    }

    @ParameterType("nom|description|pourcentage")
    public String propriété(String value){
        return value;
    }



}
