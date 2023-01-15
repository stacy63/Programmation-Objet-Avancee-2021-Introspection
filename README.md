# Introduction
L’introspection est un mécanisme puissant permettant d’analyser une classe Java, de modifier des attributs, et
d’appeler des méthodes. Dans ce TP, le but va être de construire, à partir d’une classe Java arbitraire, la structure d’une classe C++ qui compile. Il s'agit donc de 
construire un convertisseur JAVA vers C++. L’introspection ne permettant pas de lire l’intérieur d’une méthode,
seule la structure de la classe sera convertie.

# Projet
openjdk-17 est utilisé pour ce projet et un makefile a été créé avec un ensemble de trois règles afin de compiler les classes java, de les executer et de compiler le .hpp généré.

# Details
## model
Contient la classe Java Etudiant arbitraire choisie pour être convertie en classe C++.

# utils
Contient la classe Convertisseur qui rasssemble l'ensemble des méthodes nécessaire à la conversion Java vers C++.

# main
Permet d'appeler la conversion. Lors de l'execution, deux arguments peuvent être passés en paramètre. • Le premier argument doit être le nom de la classe Java à traiter. Cette classe devra se trouver dans le
classpath lors de l’execution. Le deuxième argument doit être le nom de la classe C++ à générer. Si il n’est pas fournit, le fichier devra
porter le même nom que la classe Java.

# Makefile
• make-java pour compiler le programme java.\
• run-java pour lançer la conversion Java vers C++.\
• make-cpp pour compiler le code C++ généré.


# Liens utiles
## enum dans classe cpp
https://openclassrooms.com/forum/sujet/declaration-d-enum-dans-la-classe

## Récupérer les constantes d'un enum
https://www.tutorialspoint.com/java/lang/class_getenumconstants.htm

## Liste des caractère speciaux à échaper: 
[$&+,:;=?@#|'<>.-^*()%!]
