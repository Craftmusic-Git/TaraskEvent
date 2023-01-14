# Projet Tarask

## Lancer le projet :

Pour lancer le projet il faut impérativement avoir Java 17. Et node.js v16.*. au minimum.

- lancer le docker-compose dans /infrastructure et attendre que tous les services soient up.
- Maven install
- java 
- npm run dev (Dans le cas d'une mise en production faire build puis run)

## Présentation rapide

Tarask Events est une application qui a pour but d'aider à l'organisation d'évènements
entre amis.
Son utilisation est gratuite et l'expérience doit rester simple, légère et pratique de bout
en bout.

*Note : pour que le projet ne soit pas trop lourd le reverse proxy à été enlevé*

## status de chaque features :

| Tache                                           | statut |                                                note |
|:------------------------------------------------|:------:|----------------------------------------------------:|
| Création d'un compte                            |   OK   |                                                     |
| Authentification sécurisée                      |   OK   | Utilisation de OpenID et de OAuth2.0 avec Keycloack |
| CRUD événement                                  |   OK   |                                                     |
| Modification de la configuration d'un événement |   OK   |                                                     |
| CRUD de services                                |   OK   |                                                     |
| Assignation de services                         |  API   |                                                     |
| Joindre un événement                            |  API   |                                                     |
| Suivre la progression d'un service              | API/OK |                         tache quasi fini coté front |
| Etats d'un événement                            |   OK   |                                                     |
| Scoring                                         | DOMAIN |                                                     |
| Météo                                           | DOMAIN |   Il faut implémenter la connection à une API météo |
| Lancement d'un événement                        | DOMAIN |          Le lancement d'un événement n'est pas géré |
| Rejoindre un événement avec un lien             |  API   |                                                     |


## Technologie utilisées :

### Front-end :

- Application NextJS avec Tailwind.

#### Notes : 

*Tout le front est entièrement asynchrone.*

### Back-end :

- Java Spring boot 2.7.5 ( Framework principal )
- Hibernate ( ORM )
- Lombok (Generation de code: *Getter/Setter/Constructeur*)
- Pipelinr

##  Structure du projet :

La structure du projet pour la partie Back-end est un projet multi-module représentant une architecture hexagonale.
Il est constitué de 3 modules : 
- Domain
- Persistence
- API



