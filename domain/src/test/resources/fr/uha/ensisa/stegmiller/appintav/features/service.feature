# language: fr

Fonctionnalité: Cycle de vie d'un service

Contexte:
    Etant donné un event
    Et l'organisateur de l'event

Scénario: Ajout d'un service
    Étant donné que l'event est au statut "En attente"
    * le nom d'un service
    * la description d'un service
    Quand l'organisateur ajoute un service à l'event
    Alors le service est créé
    Et le pourcentage du service est à 0%
    Et le service est ajouté aux services de l'event

Plan du scénario: Mise à jour d'un service sans responsable par l'organisateur
    Étant donné un service de l'event
    Et que le service n'a pas de responsable
    Et une nouvelle valeur pour la propriété <propriété> du service
    Quand l'organisateur met à jour la <propriété> du service
    Alors le <propriété> du service est mise à jour

    Exemples:
        | propriété    |
        | nom          |
        | description  |
        | pourcentage  |

Scénario: Mise à jour d'un service avec un responsable par l'organisateur
    Étant donné un service de l'event
    Et que le service a un responsable
    Et une nouvelle valeur pour le pourcentage
    Quand l'organisateur met à jour le pourcentage du service
    Alors le pourcentage du service est mis à jour

Scénario: Mise à jour d'un service incomplet par le responsable
    Étant donné un service de l'event
    Et que le pourcentage du service est inférieur à 100%
    Et le service a un responsable
    Et une nouvelle valeur pour le pourcentage
    Quand le responsable met à jour le pourcentage du service
    Alors le pourcentage du service est mis à jour
