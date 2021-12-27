# Ricochet-robot-solver
Implementation of the ricochet robot game, based on  A star path finder, JAVAFX framework, Gradle build tool


# Ricochet Robot Solver:

### Projet:
C'est quoi? --> Le jeu: Robot Ricochet Solver.
Langage de programmation --> Java 
Eléments importants --> Gradle, Git, A*, Tables de transposition.

### Exécution et compilation:
  Le jeu est un projet gradle. Gradle est un système de build qui permet de construire un projet java (structure et héirarchie)
  Voici quelques commandes à lancer afin d'éxécuter le projet :

  #### 3.1 Sous Linux:
``
  ./gradlew build: génération du dossier build qui contient des sous dossiers contenant les binaires ...(les outputs)
  ./gradelew run : lancer le jeu 
  ./gradelew javadoc : génération d'un dossier docs/javadoc dans build qui contient la javadoc du projet
  ./gradelew jar: génération du jar exécutable dans build/libs
  ./gradelew clean: supprimer le dossier build
  ``

  ### 3.2 Sous Windows:
   Pour lancer le jeu il suffit de lancer la commande gradle.bat run

 ### 3.3 Sous MacOs:
  Les mêmes commandes que linux mais au lieu de mettre ./gradlew run (exemple) il suffit de mettre gradle run

 ``PS: Pour lancer les commandes mentionnées ci-dessus il faut se mettre dans le dossier du projet (robot-ricochet-mc-mb-wa-ls-ss)``
