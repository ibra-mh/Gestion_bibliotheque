# projet Java Gestion_bibliotheque


## 1. Présentation du projet

Ce projet consiste à développer une application Java de gestion d'une bibliothèque universitaire avec une interface console. L'objectif est de mettre en pratique les concepts de programmation orientée objet (POO) et UML étudiés pendant le cours.

## 2. Objectif de l'application

Application console permettant de gérer:
- Les documents (Livres, Magazines, Mémoires)
- Les utilisateurs (Étudiants, Enseignants)
- Les emprunts et retours de documents
- La recherche de documents
- L'affichage des emprunts

## 3. Explication des principales classes

### Classes abstraites:
- **Document**: Classe mère avec attributs communs (id, titre, auteur, anneePublication, statut). `statut` est l'enum `Document.Statut` (`DISPONIBLE`, `EMPRUNTE`)
- **Utilisateur**: Classe mère avec attributs communs (id, nom, email)

### Classes filles (Document):
- **Livre**: nombreDePages, genre
- **Magazine**: numeroEdition, moisPublication
- **Memoire**: niveau, encadrant. `niveau` est l'enum `Memoire.Niveau` (`LICENCE`, `MASTER`, `DOCTORAT`)

### Classes filles (Utilisateur):
- **Etudiant**: filiere, niveau
- **Enseignant**: departement, specialite

### Classes métier:
- **Emprunt**: Utilisateur, Document, dateEmprunt, dateRetourPrevue
- **Gestionnaire<T>**: Classe générique (`add`, `remove`, `getAll`, `findById`) pour gérer les listes d'objets
- **BibliothequeService**: Logique principale de l'application
- **MenuConsole**: Interface utilisateur en mode console (options 1 à 10)
- **Main**: Point d'entrée (`ma.ensate.bibliotheque.app.Main`)

## 4. Collections Java utilisées

1. **ArrayList<Document>**: Stocke tous les documents
2. **ArrayList<Utilisateur>**: Stocke tous les utilisateurs
3. **ArrayList<Emprunt>**: Stocke tous les emprunts en cours
4. **HashSet<String>**: Stocke les emails pour éviter les doublons

## 5. Exceptions personnalisées

1. **DocumentIndisponibleException**: Document déjà emprunté
2. **DocumentIntrouvableException**: Document non trouvé
3. **UtilisateurIntrouvableException**: Utilisateur non trouvé
4. **EmpruntImpossibleException**: Erreur lors de l'emprunt
5. **EmailDejaExisteException**: Email déjà utilisé par un utilisateur
6. **IdDejaExisteException**: Identifiant de document ou d'utilisateur déjà utilisé
   c'est Gestion des cas d'erreur: documents non trouvés, indisponibles, emails ou identifiants dupliqués.

## 6. Organisation en packages

```
src/
└── ma/ensate/bibliotheque/
    ├── models/
    │   ├── Document.java (abstraite)
    │   ├── Livre.java
    │   ├── Magazine.java
    │   ├── Memoire.java
    │   ├── Utilisateur.java (abstraite)
    │   ├── Etudiant.java
    │   ├── Enseignant.java
    │   └── Emprunt.java
    ├── services/
    │   ├── BibliothequeService.java
    │   └── Gestionnaire.java
    ├── exceptions/
    │   ├── DocumentIndisponibleException.java
    │   ├── DocumentIntrouvableException.java
    │   ├── UtilisateurIntrouvableException.java
    │   ├── EmpruntImpossibleException.java
    │   ├── EmailDejaExisteException.java
    │   └── IdDejaExisteException.java
    └── app/
        ├── Main.java
        └── MenuConsole.java
```

## 7. Compilation et exécution

Avec Maven (Java 17 ou supérieur, aucune dépendance externe):

```
mvn package
java -jar target/gestion-bibliotheque-1.0-SNAPSHOT.jar
```

Sans Maven:

```
javac -d out $(find src -name '*.java')
java -cp out ma.ensate.bibliotheque.app.Main
```

## 8. Diagrammes UML

### Diagramme de classes
Voir fichier: `UML/Diagramme_Classes.puml`

### Diagramme de cas d'utilisation
Voir fichier: `UML/Diagramme_Cas_Utilisation.puml`

## 9. Conclusion

Ce projet démontre:
- La modélisation UML d'une application réelle
- L'utilisation des concepts POO (héritage, encapsulation, polymorphisme)
- L'utilisation des collections Java et classes génériques
- La gestion des exceptions personnalisées
- L'organisation d'un projet en packages clairs
