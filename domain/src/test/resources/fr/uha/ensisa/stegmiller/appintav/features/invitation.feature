# language: fr

Fonctionnalité: Invitation à un event

Scénario: Inscription suite à une invitation à un event
    Étant donné un utilisateur inconnu
    Et un lien d'invitation à un event
    Quand l'utilisateur accède à l'application via le lien d'invitation
    Et que l'utilisateur choisit de s'inscrire
    Alors l'utilisateur est créé
    Et l'utilisateur est ajouté aux participants de l'event

Scénario: Connexion suite à une invitation à un event
    Étant donné un utilisateur inconnu
    Et un lien d'invitation à un event
    Quand l'utilisateur accède à l'application via le lien d'invitation
    Et que l'utilisateur chosit de s'authentifier
    Alors l'utilisateur est connecté
    Et l'utilisateur est ajouté aux participants de l'event

Scénario: Invitation à un event
    Étant donné un utilisateur connecté
    Et un lien d'invitation à un event
    Quand l'utilisateur accède à l'application via le lien d'invitation
    Alors l'utilisateur est ajouté aux participants de l'event
