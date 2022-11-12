# language: fr

Fonctionnalité: Cycle de vie d'un event

Scénario: Un utilisateur crée un event
    Étant donné un utilisateur connecté
    * le nom de l'event
    * l'adresse de l'event
    Quand l'utilisateur crée l'event
    Alors l'event est créé
    Et l'event est au statut "Organisation"
    Et l'utilisateur est organisateur de l'event

Plan du scénario: Organiser l'event
    Étant donné un event
    Et que l'event est au statut "Organisation"
    Et son organisateur
    Et une <information> pour une <propriété> de l'event
    Quand l'organisateur met à jour la <propriété> de l'event avec l'<information>
    Alors la <propriété> de l'event est mise à jour avec l'<information>

    Exemples:
        | propriété    | information |
        | date         | 27/09/2023  |
        | capacité     | 15          |
        | en exterieur | non         |
        | age limite   | 15          |

Scénario: Un event est complètement renseigné
    Étant donné un event
    Et que l'event est au statut "Organisation"
    Et son organisateur
    Quand l'organisateur met à jour l'event
    Et que l'event est complètement renseigné
    Alors l'event est au statut "En attente"
    Et le scoring est calculé

    Plan du scénario: mettre à jour le scoring
        Étant donné un event
        Et que l'event est au statut <statut>
        Et son organisateur
        Quand l'organisateur met à jour l'event
        Alors le scoring est recalculé

        Exemples:
            | statut     |
            | En attente |
            | En cours   |

Scénario: Validation d'un event sans service
    Étant donné un event
    Et que l'event est au statut "En attente"
    Et que l'event n'a pas de service
    Et l'organisateur de l'event
    Quand l'organisateur valide l'event
    Alors l'event est au statut "Prêt"

Scénario: Validation d'un event avec tous les services à 100%
    Étant donné un event
    Et que l'event est au statut "En attente"
    Et que l'event a au moins un service
    Et tous les services de l'event ont un pourcentage de 100%
    Et l'organisateur de l'event
    Quand l'organisateur valide l'event
    Alors l'event est au statut "Prêt"

Scénario: Validation d'un event avec des services incomplets
    Étant donné un event
    Et que l'event est au statut "En attente"
    Et que l'event a au moins un service
    Et qu'au moins un service de l'event a un pourcentage inférieur à 100%
    Et l'organisateur de l'event
    Quand l'organisateur valide l'event
    Alors l'event est au statut "En cours"

Scénario: Complétion du dernier service
    Étant donné un event
    Et que l'event est au statut "En cours"
    Et un service de l'event
    Et le responsable du service
    Quand le responsable du service modifie le pourcentage du service à 100%
    Et que tous les services de l'event ont un pourcentage de 100%
    Alors l'event est au statut "Prêt"
