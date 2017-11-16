# WebServicesMobileCarSharing

[![N|Solid](https://github.com/jbrat/SocialNetworkUJM-SpringReact/blob/master/src/main/resources/static/images/logo_ujm.png?raw=true)](https://www.univ-st-etienne.fr/fr/index.html)

[![N|Solid](http://rubenjgarcia.es/wp-content/uploads/2016/09/springboot.png)](https://projects.spring.io/spring-boot/)

Ce mini projet de l'UE du Master 2 Données et Systèmes Connectés en Web Services ("Applications Distribuees") a pour but de creer avec Spring, des graphiques à l'aide de la librairies amcharts.com, de manipuler les differentes tables de la base de donnee et de creer des comptes username/password a l'aide de Spring Security.

## Import the Database

Tout d'abord, il faut importer la database "carsharing.sql" dans votre base de donnee. Ce fichier se situe dans src/main/resources.

Ensuite, vous devez modifier le mot de passe de la base de donnee qui se trouve dans "application.properties." (et éventuellement, le user: par défaut, il est mis a "root").

### Spring Security

Dans ce projet nous avons utilisés Spring Security, pour pouvoir utilisé des comptes pour ce site avec le framework Spring.
Les mots de passes sont cryptés pour chaque utilisateur, avec BCryptPasswordEncoder.

### Users and Roles in the Database

Il existe 3 types de roles pour les utilisateurs : 
* ADMIN : Il a tous les droits
* USER : Cette utilisateur est restreint.


```
L'utilisateur avec le username:  useradmin et password: useradmin correspond a un compte avec le role d'admin.
L'utilisateur avec le username:  useruser et password: useruser correspond a un compte avec le role de user.
```


## Fonctionnalités

### En tant que user connecte

Il existe les fonctionnalités suivantes pour ce projet : 

* L'Admin peut modifier le role des utilisateurs de son choix. (Sauf lui-même). "Profil dans la barre du menu".
* Chaque utilisateur peut modifier son password. "Your Profil dans la barre du menu".
* Chaque utilisateur peut supprimer son compte (définitivement) avec une pop-up de confirmation. "Delete Account dans la barre du menu".
* Chaque utilisateur peut se deconnecter a tout moment. "Logout dans la barre du menu".
* L'acces a la description du site ce fait via "Description dans la barre du menu".
* L'acces a une eventuel aide sur le site ce fait via "Help dans la barre du menu".

### En tant que user non connecte

Il existe les fonctionnalites suivantes pour ce projet : 

* L'utilisateur peut se connecter via "Sign in dans la barre de menu."
* L'utilisateur peut s'enregistrer un nouveau compte via "Sign out dans la barre de menu." Lors de la creation et de la validation de celui-ci, il y a un autologin qui se cree et l'utilisateur est connecte sans avoir a retaper les informations du nouveau compte qu'il vient tout juste de crée.
* L'acces a la description du site ce fait via "Description dans la barre du menu".
* L'acces a une eventuel aide sur le site ce fait via "Help dans la barre du menu".

## Validation de formulaire 

Dans le projet nous avons mis en place des validations de formulaires, notamment : 

* Pour le "Sign in dans la barre du menu" si le username et password ne correspond pas aux données, on affiche un message en rouge sur la page.
* Pour le "Sign Out dans la barre du menu", si le username n'est pas compris entre 6 et 32 caracteres, on affiche un message d'erreur en rouge sur la page.
* Pour le "Sign Out dans la barre du menu", on verifie si les deux passwords correspondent aux mêmes, sinon on affiche un message d'erreur en rouge sur la page.
* Pour modifier le password, "Your profil dans la barre du menu and on Update button", on verifie si les deux mots de passes correspondent l'un à l'autre, sinon on affiche un message d'erreur en rouge sur la page.

## Les pages html

Toutes les pages html se trouvent dans src/main/resources/templates.

## Authors

* **Kevin ABRIAL**


* **Amine IDIR**

* **Alexis BARTHELEMY**
