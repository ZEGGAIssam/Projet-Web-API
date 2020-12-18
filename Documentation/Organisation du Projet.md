# Le Cahier des Charges
Membres du groupe : **Mathieu DZIEDZIC, Aimé SCHNEIDER, Issam ZEGGAÏ**

# Tableaux des spécifications :
![Screen](https://i.imgur.com/TXaAUTN.jpg)


# Features supplémentaires :

Le projet inclus un :
- Système de login
- Système d'enregistrement d'un user et de rename de l'user (Nom Prénom par exemple)
- Système de vote et de sondage (Création de sondage - Suppression de sondage - Liste de sondage - Voter)
- Changement de pseudo (update user)
- API Springboot & Base de donnée FIREBASE

# Choix de la Database 

Pourquoi avoir choisi Firebase pour ce projet ?

Tout d'abord car l'un des membres du groupe était plutôt à l'aise avec cette technologie @ZEGGAIssam, mais qu'également, la documentation de Firebase est relativement complète et propose un panel de solutions et d'intégrations pour les différentes utilisations que nous souhaitons en faire.
D'une base de donnée basique (version Free), à une version beaucoup plus élaborée, La BDD Firebase propose des solutions "tout-en-un" facilitant leurs développeurs à s'occuper le moins possible de leur back-end via une automatisation de ce dernier (Authentification, stockage de la data, cloudification de la data etc..)
Le problème étant que l'établissement de la BDD nous a pris un temps considérable afin de les link à nos différentes features que nous avons mis en place au fur et à mesure de l'avancement du projet.

# Description de la démarche de production de la liste des exigences :

Afin de produire le tableaux des spécifications mentionnés plus haut, nous nous sommes tout d'abord basé sur les grandes lignes du projet, 
qui sont de :
- Permettre à l'utilisateur de s'enregistrer via le site
- Permettre à l'utilisateur de se rename
- Permettre à l'utilisateur de créer un sondage, de le supprimer, de visualiser la liste des sondages mais également de voter pour un rendez-vous.

Suite à quoi nous avons trier les différentes exigences de ce dernier afin de produire le tableaux des spécifications que vous voyez plus haut.

# Backlog User Stories & Constraint Stories :
![Screen](https://i.imgur.com/a65JAXS.png)
