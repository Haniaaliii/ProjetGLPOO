!! IMPORTANT !!
Les seules musiques écoutables sont celles présentes dans le dossier music c'est à dire : Friday, Mamma Mia, Dancing Queen.
Pour pouvoir tester l'application il faudra donc utiliser ces dernières

Marche parfaitement sur eclipse.

Installation sur eclipse :

Importer le projet : 

Eclipse -> nouveau Java Project -> selectionner le dossier ProjetGLPOO

Lancer le projet :

-Lancer le Serveur en premier : src->serveur->main->Main.java
-Lancer ensuite le Client : src->client->musichub->Main.java

------------------------------------------------------------------------------------

Lancement sans eclipse :

lancer le serveur en premier à l'aide du fichier lancerServeur.bat

lancer le client à l'laide du fichier lancerClient.bat

------------------------------------------------------------------------------------


Errors :

Si erreur : java.lang.UnsatisfiedLinkError: no icedtea-sound in java.library.path
Solution : apt-get install openjdk-8-jre openjdk-8-jdk
