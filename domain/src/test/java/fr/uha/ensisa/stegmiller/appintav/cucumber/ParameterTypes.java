package fr.uha.ensisa.stegmiller.appintav.cucumber;

import fr.uha.ensisa.stegmiller.appintav.model.Event.Statut;
import fr.uha.ensisa.stegmiller.appintav.model.Organisation;
import io.cucumber.java.ParameterType;

public class ParameterTypes {

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
    public Organisation.Day day(String value){
        switch (value){
            case "Lundi" -> { return Organisation.Day.MONDAY; }
            case "Mardi" -> { return Organisation.Day.TUESDAY; }
            case "Mercredi" -> { return Organisation.Day.WEDNESDAY; }
            case "Jeudi" -> { return Organisation.Day.THURSDAY; }
            case "Vendredi" -> { return Organisation.Day.FRIDAY; }
            case "Samedi" -> { return Organisation.Day.SATURDAY; }
            case "Dimanche" -> { return Organisation.Day.SUNDAY; }
            default -> { return null; }
        }
    }

    @ParameterType("soleil|neige|tempête|pluie")
    public Organisation.Weather weather(String value){
        switch (value){
            case "soleil" -> { return Organisation.Weather.SUNNY; }
            case "neige" -> { return Organisation.Weather.SNOWING; }
            case "tempête" -> { return Organisation.Weather.TEMPEST; }
            case "pluie" -> { return Organisation.Weather.RAINING; }
            default -> { return null; }
        }
    }

    @ParameterType("nom|description|pourcentage")
    public String propriété(String value){
        return value;
    }



}
