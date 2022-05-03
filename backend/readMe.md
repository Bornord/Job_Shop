# Manuel d'utilisation backend

Suite à l'interdiction de travailler sur la technologie node pour le backend, nous avons donc fait le backend en Java.
Afin de permettre un développement modulaire, nous avons créer un backend supplémentaire pour faire les tests.

## Backend Java

## Backend de test Node.js

Ce serveur sert d'interface de test.
Il permet au frontend d'appeler de façon simple le backend avec un déploiement minime.

Concrètement, ce backend écoute continuellement sur un port local.
Il enregistre le appel serveur qui lui sont fait et il renvoie un message classique.

Lorsque les tests "temps réels" seront à faire avec le backend Java, il suffira de changer uniquement les adresses d'appel dans le frontend.
Ce backend tourne via l'API express et le framework axios.

### Lancer le testeur

> nodemon Server
> (Persistance & auto-update du backend)

OU

> yarn start
> (Persistance sans update du backend)

### Préparation et import de package

-   Importer le package express
    > npm install express
