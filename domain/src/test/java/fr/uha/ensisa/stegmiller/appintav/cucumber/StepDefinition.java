package fr.uha.ensisa.stegmiller.appintav.cucumber;

import fr.uha.ensisa.stegmiller.appintav.command.event.*;
import fr.uha.ensisa.stegmiller.appintav.command.favor.CreateFavorCommand;
import fr.uha.ensisa.stegmiller.appintav.command.favor.CreateFavorCommandHandler;
import fr.uha.ensisa.stegmiller.appintav.command.favor.FavorTakenByUserCommand;
import fr.uha.ensisa.stegmiller.appintav.command.favor.FavorTakenByUserCommandHandler;
import fr.uha.ensisa.stegmiller.appintav.command.user.*;
import fr.uha.ensisa.stegmiller.appintav.mocking.MockEventService;
import fr.uha.ensisa.stegmiller.appintav.mocking.MockFavorService;
import fr.uha.ensisa.stegmiller.appintav.mocking.MockUserService;
import fr.uha.ensisa.stegmiller.appintav.model.Address;
import fr.uha.ensisa.stegmiller.appintav.model.Event;
import fr.uha.ensisa.stegmiller.appintav.model.Favor;
import fr.uha.ensisa.stegmiller.appintav.model.User;
import fr.uha.ensisa.stegmiller.appintav.service.EventService;
import fr.uha.ensisa.stegmiller.appintav.service.FavorService;
import fr.uha.ensisa.stegmiller.appintav.service.UserService;
import io.cucumber.java.Before;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Quand;
import io.cucumber.java.fr.Étantdonné;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

import static fr.uha.ensisa.stegmiller.appintav.cucumber.UserConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class StepDefinition {

    // =============== Services ================
    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @Autowired
    FavorService favorService;

    // ============= CommandHandler =============
    @Autowired
    CreateUserCommandHandler createUserCommandHandler;
    @Autowired
    AuthentificationUserCommandHandler authentificationUserCommandHandler;
    @Autowired
    RemoveUserCommandHandler removeUserCommandHandler;
    @Autowired
    JoinEventCommandHandler joinEventCommandHandler;
    @Autowired
    FavorTakenByUserCommandHandler favorTakenByUserCommandHandler;
    @Autowired
    CreateFavorCommandHandler createFavorCommandHandler;
    @Autowired
    CreateEventCommandHandler createEventCommandHandler;
    @Autowired
    UpdateEventOrganisationCommandHandler updateEventOrganisationCommandHandle;

    // ============= Local Var =============
    User user;
    User processUser;
    Event event;
    Favor favor;
    CreateUserCommand createUserCommand;
    AuthentificationUserCommand authentificationUserCommand;
    Exception exception;
    Address eventAddress;
    String eventName;
    Event processEvent;
    UpdateEventOrganisationCommand.Property updateProperty;
    Object updateInformation;

    Error error;

    // ============= Cucumber Methods

    @Before
    public void before(){
        ((MockEventService)eventService).setCounter(0L);
        ((MockEventService)eventService).setEvents(new ArrayList<>());
        ((MockUserService)userService).setCounter(0L);
        ((MockUserService)userService).setUsers(new ArrayList<>());
        ((MockFavorService)favorService).setCounts(0L);
        ((MockFavorService)favorService).setFavors(new ArrayList<>());
    }
    // ================= utils ==================


    // ============= Et/Etant donné =============

    @Étantdonné("un utilisateur")
    public User unUtilisateur(){
        return new User(USER_STD_NAME, USER_STD_FIRSTNAME, USER_STD_BIRHTDATE);
    }

    @Étantdonné("un utilisateur inconnu")
    public void unUtilisateurInconnu() {
        user = new User();
        user.setStatus(User.Status.UNKNOWN);
    }

    @Étantdonné("un utilisateur connecté")
    public void unUtilisateurConecte(){
        user = unUtilisateur();
        user.setStatus(User.Status.CONNECTED);
        user = userService.createUser(user);
    }

    @Étantdonné("un event")
    public void unEvent(){
        event = new Event();
    }

    @Étantdonné("un formulaire d'inscription valide")
    public void unFormulairedInscriptionValide() {
        user.setName(USER_STD_NAME);
        user.setFirstname(USER_STD_FIRSTNAME);
        user.setBirthdate(USER_STD_BIRHTDATE);
        createUserCommand = new CreateUserCommand(user);
    }

    @Etantdonné("un formulaire d'authentification valide")
    public void unFormulairedAuthentificationValide() {
        user.setName(USER_STD_NAME);
        user.setFirstname(USER_STD_FIRSTNAME);
        user.setBirthdate(USER_STD_BIRHTDATE);
        userService.createUser(user);
        authentificationUserCommand = new AuthentificationUserCommand(user);
    }

    @Etantdonné("il n'est organisateur d'aucun évènement en cours")
    public void ilnEstOrganisteurdAucunEvenementEnCours(){
        user.setEventOrganized(new ArrayList<>());
    }

    @Etantdonné("l'event est au statut \"Prêt\"")
    public void lEventEstAuStatutPret(){
        event.setStatut(Event.Statut.READY);
    }

    @Etantdonné("l'utilisateur n'est pas l'organisateur de l'event")
    public void lUtilisateurnEstPaslOrganisateurDelEvent(){

    }

    @Etantdonné("l'event est au statut \"En cours\"")
    public void lEventEstAuStatusEnCours(){
        event.setStatut(Event.Statut.IN_PROGRESS);
    }

    @Etantdonné("un service de l'event sans responsable")
    public void unServiceDelEventSansResponsable(){
        favor = new Favor(FAVOR_TITLE,FAVOR_DESCRIPTION);
        favor = createFavorCommandHandler.handle(new CreateFavorCommand(event, favor));
    }

    @Etantdonné("le nom de l'event")
    public void leNomDelEvent(){
        eventName = EVENT_NAME;
    }

    @Etantdonné("l'adresse de l'event")
    public void lAdresseDelEvent(){
        eventAddress = EVENT_ADDRESS;
    }

    @Etantdonné("l'event est au statut \"Organisation\"")
    public void etantlEventEstAuStatutOrganisation(){
        unUtilisateurConecte();
        lAdresseDelEvent();
        lUtilisateurCreelEvent();
        event = processEvent;
        processEvent = null;
    }

    @Etantdonné("son organisateur")
    public void sonOrganisateur(){

    }

    @Étantdonné("une {date} pour une date de l'event")
    public void une_pour_une_date_de_l_event(Date date) {
        updateInformation = date;
        updateProperty = UpdateEventOrganisationCommand.Property.DATE;
    }

    @Étantdonné("une {int} pour une capacité de l'event")
    public void une_pour_une_capacité_de_l_event(Integer int1) {
        updateInformation = int1;
        updateProperty = UpdateEventOrganisationCommand.Property.CAPACITY;
    }

    @Étantdonné("une {bool} pour une en exterieur de l'event")
    public void une_non_pour_une_en_exterieur_de_l_event(Boolean bool) {
        updateInformation = bool;
        updateProperty = UpdateEventOrganisationCommand.Property.EXTERN;
    }

    @Étantdonné("une {int} pour une age limite de l'event")
    public void une_pour_une_age_limite_de_l_event(Integer int1) {
        updateInformation = int1;
        updateProperty = UpdateEventOrganisationCommand.Property.LIMIT_AGE;
    }

    @Etantdonné("que l'event est au statut {statut}")
    public void quelEventEstAuStatut(Event.Statut statut){
        event.setStatut(statut);
    }

    // ================ Quand ================

    @Quand("il accède à l'application")
    public void ilAccedeAlApplication(){
        // Peut seulement être implémenté via un test d'intégration
    }

    @Quand("le formulaire d'inscription est envoyé")
    public void leFormulairedInscriptionEstEnvoye(){
        try{
            processUser = createUserCommandHandler.handle(createUserCommand);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Quand("le formulaire de connexion est envoyé")
    public void leFormulaireDeConnexionEstEnvoye(){
        try {
            processUser = authentificationUserCommandHandler.handle(authentificationUserCommand);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Quand("il demande à supprimer son compte")
    public void ilDemandeASupprimerSonCompte(){
        user = unUtilisateur();
        processUser = userService.createUser(user);
        try {
            removeUserCommandHandler.handle(new RemoveUserCommand(processUser));
        } catch (Exception e) {
            exception = e;
        }
    }

    @Quand("l'utilisateur rejoins l'event")
    public void lUtilisateurRejoinlEvent(){
        JoinEventCommand command = new JoinEventCommand(event, user);
        try {
            joinEventCommandHandler.handle(command);
        } catch (Exception e){
            exception = e;
        }
    }

    @Quand("l'utilisateur prend en charge le service")
    public void lUtilisateurEstAjouteAuxParticipantsDelEvents(){
        try {
            favorTakenByUserCommandHandler.handle(new FavorTakenByUserCommand(user,event,favor));
        } catch (Exception e){
            exception = e;
        }
    }

    @Quand("l'utilisateur crée l'event")
    public void lUtilisateurCreelEvent(){
        try {
            processEvent = createEventCommandHandler.handle(new CreateEventCommand(user,new Event(eventName, eventAddress)));
        } catch (Exception e){
            exception = e;
        }
    }

    @Quand("l'organisateur met à jour la date de l'event avec l'{date}")
    public void l_organisateur_met_à_jour_la_date_de_l_event_avec_l(Date date) {
        try {
            processEvent = updateEventOrganisationCommandHandle.handle(new UpdateEventOrganisationCommand(event, user, updateProperty, updateInformation));
        } catch (Exception e){
            exception = e;
        } catch (Error e){
            error = e;
        }
    }

    @Quand("l'organisateur met à jour la capacité de l'event avec l'{int}")
    public void l_organisateur_met_à_jour_la_capacité_de_l_event_avec_l(Integer int1) {
        try {
            processEvent = updateEventOrganisationCommandHandle.handle(new UpdateEventOrganisationCommand(event, user, updateProperty, updateInformation));
        } catch (Exception e){
            exception = e;
        }
    }

    @Quand("l'organisateur met à jour la en exterieur de l'event avec l'{bool}")
    public void l_organisateur_met_à_jour_la_en_exterieur_de_l_event_avec_l_non(Boolean bool) {
        try {
            processEvent = updateEventOrganisationCommandHandle.handle(new UpdateEventOrganisationCommand(event, user, updateProperty, updateInformation));
        } catch (Exception e){
            exception = e;
        }
    }

    @Quand("l'organisateur met à jour la age limite de l'event avec l'{int}")
    public void l_organisateur_met_à_jour_la_age_limite_de_l_event_avec_l(Integer int1) {
        try {
            processEvent = updateEventOrganisationCommandHandle.handle(new UpdateEventOrganisationCommand(event, user, updateProperty, updateInformation));
        } catch (Exception e){
            exception = e;
        }
    }

    @Quand("l'organisateur met à jour l'event")
    public void lOrganisateurMetAJourlEvent(){
        try {
            processEvent = updateEventOrganisationCommandHandle.handle(new UpdateEventOrganisationCommand(event, user, UpdateEventOrganisationCommand.Property.DATE, EVENT_DATE));
        } catch (Exception e){
            exception = e;
        }
    }

    @Quand("l'event est complètement renseigné")
    public void lEventEstCompletementRenseigne(){

    }

    // ================ Alors ================

    @Alors("l'application lui demande de s'inscrire ou de se connecter")
    public void lApplicationLuiDemandeDesInscrireOuDeSeConnecter(){
        // Peut seulement être implémenté avec un test d'intégration
    }

    @Alors("l'utilisateur est créé")
    public void lUtilisateurEstCree(){
        assertNotNull(processUser);
        assertEquals(user.getName(),processUser.getName());
        assertEquals(user.getFirstname(),processUser.getFirstname());
        assertEquals(user.getArchived(),processUser.getArchived());
        assertEquals(user.getBirthdate(),processUser.getBirthdate());
    }

    @Alors("l'utilisateur est connecté")
    public void lUtilisateurEstConnecte(){
        assertEquals(user.getStatus(), User.Status.CONNECTED);
    }

    @Alors("l'utilisateur est supprimé de tous les évènements auxquels il participe")
    public void lUtilisteurEstSupprimeDeTousLesEvenementsAuxquelsIlParticipe(){
        assertTrue(eventService.getEventOfSpecifiedUserID(processUser.getId()).isEmpty());
    }

    @Alors("son compte est archivé")
    public void sonCompteEstArchive(){
        assertThrows(Error.class,() ->userService.getUserById(processUser.getId()));
        assertTrue(processUser.getArchived());
    }

    @Alors("l'utilisateur est ajouté aux participants de l'event")
    public void alorslUtilisateurEstAjouteAuxParticipantsDelEvent(){
        boolean isContain = false;
        for(var u : event.getGuests()){
            if (Objects.equals(u.getId(), user.getId())) {
                isContain = true;
                break;
            }
        }
        assertTrue(isContain);
    }

    @Alors("l'utilisateur devient responsable du service")
    public void lUtilisateurDevientResponsableDuService(){
        boolean isResponsable = false;
        for(var entry : event.getFavors().entrySet()){
            if (Objects.equals(entry.getValue().getId(), user.getId())) {
                isResponsable = true;
                break;
            }
        }
        assertTrue(isResponsable);
    }

    @Alors("l'event est créé")
    public void lEventEstCree(){
        assertNotNull(processEvent);
    }

    @Alors("l'event est donc au statut \"Organisation\"")
    public void lEventEstAuStatutOrganisation(){
        assertEquals(Event.Statut.CONFIGURATION, processEvent.getStatut());
    }

    @Alors("l'utilisateur est organisateur de l'event")
    public void lUtilisateurEstOrganisateurDelEvent(){
        boolean isOrganist = false;
        for(var e : user.getEventOrganized())
            if (Objects.equals(e.getId(), processEvent.getId())) {
                isOrganist = true;
                break;
            }
        assertTrue(isOrganist);
    }

    @Alors("la date de l'event est mise à jour avec l'{date}")
    public void la_date_de_l_event_est_mise_à_jour_avec_l(Date date) {
        assertEquals(processEvent.getOrganization().getDate(), date);
    }

    @Alors("la capacité de l'event est mise à jour avec l'{int}")
    public void la_capacité_de_l_event_est_mise_à_jour_avec_l(Integer int1) {
        assertEquals(processEvent.getOrganization().getCapacity(), int1);
    }

    @Alors("la en exterieur de l'event est mise à jour avec l'{bool}")
    public void la_en_exterieur_de_l_event_est_mise_à_jour_avec_l_non(Boolean bool) {
        assertEquals(processEvent.getOrganization().getIsOutside(), bool);
    }

    @Alors("la age limite de l'event est mise à jour avec l'{int}")
    public void la_age_limite_de_l_event_est_mise_à_jour_avec_l(Integer int1) {
        assertEquals(processEvent.getOrganization().getAgeLimit(), int1);
    }

    @Alors("l'event est au statut \"En attente\"")
    public void lEventEstAuStatutEnAttente(){
        assertEquals(Event.Statut.WAITING,processEvent.getStatut());
    }

    @Alors("le scoring est calculé")
    public void leScoringEstCalcule(){
        assertNotEquals(0, event.getOrganization().getScoring().getGlobalScore());
    }

    @Alors("le scoring est recalculé")
    public void leScoringEstRecalcule(){
        assertNotEquals(0, event.getOrganization().getScoring().getGlobalScore());
    }
}