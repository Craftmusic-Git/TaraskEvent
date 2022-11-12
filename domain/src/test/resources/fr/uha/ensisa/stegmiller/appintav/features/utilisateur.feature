# language: fr

Fonctionnalité: Accueil d'un utilisateur inconnu

Scénario: Connexion obligatoire
    Étant donné un utilisateur inconnu
    Quand il accède à l'application
    Alors l'application lui demande de s'inscrire ou de se connecter

Scénario: Inscription
    Étant donné un utilisateur inconnu
    Et un formulaire d'inscription valide
    Quand le formulaire d'inscription est envoyé
    Alors l'utilisateur est créé
    Et l'utilisateur est connecté

Scénario: Connexion
    Étant donné un utilisateur inconnu
    Et un formulaire d'authentification valide
    Quand le formulaire de connexion est envoyé
    Alors l'utilisateur est connecté

Scénario: Suppression de compte
    Étant donné un utilisateur connecté
    Et il n'est organisateur d'aucun évènement en cours
    Quand il demande à supprimer son compte
    Alors l'utilisateur est supprimé de tous les évènements auxquels il participe
    Et son compte est archivé

Scénario: Participation à un event
    Étant donné un event
    Et que l'event est au statut "Prêt"
    Et un utilisateur connecté
    Et que l'utilisateur n'est pas l'organisateur de l'event
    Quand l'utilisateur rejoins l'event
    Alors l'utilisateur est ajouté aux participants de l'event

Scénario: Prise en charge d'un service
    Étant donné un event
    Et que l'event est au statut "En cours"
    Et un service de l'event sans responsable
    Et un utilisateur connecté
    Quand l'utilisateur prend en charge le service
    Alors l'utilisateur est ajouté aux participants de l'event
    Et l'utilisateur devient responsable du service
