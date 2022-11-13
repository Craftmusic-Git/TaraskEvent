package fr.uha.ensisa.stegmiller.appintav.cucumber;

import fr.uha.ensisa.stegmiller.appintav.model.Address;
import fr.uha.ensisa.stegmiller.appintav.model.value.City;
import fr.uha.ensisa.stegmiller.appintav.model.value.CityCode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class UserConstants {
    public static final String USER_STD_NAME = "DUPONT";
    public static final String USER_STD_FIRSTNAME = "Jean";
    public static final Date USER_STD_BIRHTDATE;
    public static final String FAVOR_TITLE = "Boissons";
    public static final String FAVOR_DESCRIPTION = "Ammener les boissons";
    public static final String EVENT_NAME = "Anniversaire";
    public static final Address EVENT_ADDRESS;
    public static final Date EVENT_DATE;
    public static final String USER_FAVOR_MANAGER_NAME = "LOUP";
    public static final String USER_FAVOR_MANAGER_FIRSTNAME = "Camille";
    public static final Date USER_FAVOR_MANAGER_BIRTHDATE;
    static {
        try {
            USER_STD_BIRHTDATE = new SimpleDateFormat("dd/MM/yyyy").parse("01/07/2000");
            EVENT_DATE = new SimpleDateFormat("dd/MM/yyyy").parse("01/07/2023");
            USER_FAVOR_MANAGER_BIRTHDATE = new SimpleDateFormat("dd/MM/yyyy").parse("24/09/2001");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        EVENT_ADDRESS = new Address();
        EVENT_ADDRESS.setCity(new City("Strasbourg"));
        EVENT_ADDRESS.setCode(new CityCode("67000"));
        EVENT_ADDRESS.setWay("Rue des bouchers");
        EVENT_ADDRESS.setNumber(42);
    }

}
