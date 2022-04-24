# Document pour le suivi de projet

# Table des échéances

-   Échéance 1 : 8 Avril 2022

# Formalité

Le titre des mails à Hagimont doit être le même.

> JEE - NICOLAS - RENARD - SAYADI - SANCHEZ PENA - BEST

## Échéance 1 : début du projet

Nous travaillons sur le projet JobShop.

Lors de cette première semaine, nous avons commencé à configurer le projet. Concrètement nous avons traité les sujets suivants :

-   Définition du projet et cahier des charges du siteweb.
    Le site web doit permettre de mettre en relation le recruteur avec l'étudiant qui lui correspond.
    Le slogan, c'est "arrêter de chercher des gens qui ne correspondent pas à vos critères".
    Pour les étudiants, nous proposons des offres de stages personnalisés avec des suggestions en fonction de leur goûts et de leurs profils.
    Nous permettons aux recruteurs d'avoir des candidats plus qualifiés.
    Il faut également que l'outil mette en relation facilement les recruteurs et les étudiants.
-   Définition des besoins des élèves.
    Il faut créer un formulaire personnalisé pour trouver les offres les plus pertinentes et les afficher.
    Un candidat doit pouvoir consulter la globalité des résultats et appliquer des filtres, ou bien modifier les résultats du questionnaire pour affiner.
-   Définition des recruteurs. Avoir des contacts d'étudiants à contacter selon les critères qui les intéressent.
-   Ci-joint se trouve une architecture théorique du frontend et du backend.
    Nous avons déjà réfléchis à la cybersécurité de notre AW. Quelques réflexions apparaîssent sur l'architecture
-   En ce qui concerne le schéma Entity-Bean. Le schéma et les specifications ne sont pas encore précises.
    Concrètement, nous avons besoin d'une BDD pour :
    -   Les élèves
    -   Les offres de candidatures
        -> Possibilité de se brancher à d'autres APIs pour recupérer les offres de stage.
    -   Les fichiers (CVs, lettres de motivation, etc.)
-   Création du dépôts Git & déploiement des outils de développement web (prettier, eslint, classe "proxy" etc.) qui permettront de faciliter la collaboration au sein du projet.
    Prochains objectifs :
    -   Définir les besoins Entity beans de façon plus fine.
    -   Définir l'architecture frontend.
    -   Séparer le travail en fonction des personnes.
    -   Établir le contenu des premières itérations pour déployer le site au plus vite.

## Échéance 2 : précision du cahier des charges

Nous travaillons sur le projet JobShop.
Cette semaine, nous avons définit plus précisément le cahier des charges de l'application, les services proposés pour les étudiants à la recherche d'un stage / job, et les services proposés pour les recruteurs. Ainsi, un étudiant est complètement passif après qu'il ait passé les étapes de création de compte, réponse aux questions et ajout de CV et le recruteur reçoit le moyen de contacter un étudiant qui "match" le plus avec une des offres qu'il a publié.
En pièce jointe le diagramme UML des classes que nous avons commencé à implémenter en Java.
Nous avons également répartit les tâches entre nous. Ainsi 2 d'entre nous travaillent plutôt sur le FrontEnd et 2 autres sur le BackEnd. Le 5ème membre fait le lien entre les 2 sous groupes afin d'assurer la cohérence des travaux fournis.

## Échéance 3 : Mise en place des classes pour préparer le remplissage du code.

Nous avons commencé le code afin de modéliser les comportements et les classes JS & Java

-   CSS global, mise en place des classes.
-   Mise en place de la barre de navigation, de l'espace de login et de signup.
-   On a fait un début de index.html (qu'on changera comme il faut plus tard) pour rentrer des données (genre données propres à un étudiant ) puis les afficher en format JSON pour la communication avec le frontend en vue des class proxy de modélisation des échanges front / back.
-   Mise en place des Reducers et du data store

Pb : déployer de façon continue le backend JBOSS afin de faire des appels H24.
