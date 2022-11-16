# language: fr

Fonctionnalité: Calcul du scoring

Plan du scénario: Calculer le scoring
    Etant donné un event
    Et que l'event est au statut "En attente"
    Et le <jour de la semaine> de la date de l'event
    Et la date de l'event tombe pendant les <vacances> ou un jour férié
    Et l'état <en extérieur> de l'event
    Et la <météo> prévue
    Et la <température> prévue
    Quand le scoring de l'event est calculé
    Alors le scoring météo vaut <note météo>
    Et le scoring date vaut <note date>
    Et le scoring vaut <note>

    Exemples:
        | jour de la semaine | vacances | en extérieur | météo   | température | note météo | note date | note |
        | Lundi              | non      | non          | soleil  | 20          | 5          | 0         | ??   |
        | Mardi              | non      | non          | soleil  | 20          | 5          | 0         | ??   |
        | Mercredi           | non      | non          | soleil  | 20          | 5          | 0         | ??   |
        | Jeudi              | non      | non          | soleil  | 20          | 5          | 0         | ??   |
        | Vendredi           | non      | non          | soleil  | 20          | 5          | 0         | ??   |
        | Samedi             | non      | non          | soleil  | 20          | 5          | 3         | ??   |
        | Dimanche           | non      | non          | soleil  | 20          | 5          | 3         | ??   |
        | Samedi             | oui      | non          | soleil  | 20          | 5          | 5         | ??   |
        | Dimanche           | oui      | non          | soleil  | 20          | 5          | 5         | ??   |
        | Lundi              | oui      | non          | soleil  | 20          | 5          | 2         | ??   |
        | Lundi              | non      | oui          | soleil  | 20          | 5          | 0         | ??   |
        | Lundi              | non      | oui          | pluie   | 20          | 0          | 0         | ??   |
        | Lundi              | non      | oui          | neige   | 20          | 0          | 0         | ??   |
        | Lundi              | non      | oui          | tempête | 20          | 0          | 0         | ??   |
        | Lundi              | non      | oui          | soleil  | 5           | 0          | 0         | ??   |
        | Lundi              | non      | oui          | soleil  | 40          | 3          | 0         | ??   |
        | Lundi              | non      | non          | pluie   | 20          | 5          | 0         | ??   |
        | Lundi              | non      | non          | neige   | 20          | 3          | 0         | ??   |
        | Lundi              | non      | non          | tempête | 20          | 3          | 0         | ??   |
        | Lundi              | non      | non          | soleil  | 5           | 5          | 0         | ??   |
        | Lundi              | non      | non          | soleil  | 45          | 3          | 0         | ??   |
