# Questions et Réponses du Projet

## Question 1: Quelle est la structure du projet et pourquoi ce choix d'organisation?

**Réponse:**
Le projet suit une organisation standard Java avec une hiérarchie de packages:
- `ma.ensate.bibliotheque.models`: Classes métier (Document, Livre, Magazine, Memoire, Utilisateur, Etudiant, Enseignant, Emprunt)
- `ma.ensate.bibliotheque.services`: Logique métier (BibliothequeService, Gestionnaire)
- `ma.ensate.bibliotheque.exceptions`: Exceptions personnalisées
- `ma.ensate.bibliotheque.app`: Interface console (MenuConsole) et point d'entrée (Main)

Ce choix permet une séparation claire des responsabilités (séparation des préoccupations) conformément aux bonnes pratiques de développement.

## Question 2: Comment l'héritage est exploité dans ce projet?

**Réponse:**
Deux hiérarchies d'héritage ont été créées:

1. **Document** (classe abstraite) → **Livre**, **Magazine**, **Memoire**
2. **Utilisateur** (classe abstraite) → **Etudiant**, **Enseignant**

Les classes filles héritent des attributs communs et peuvent être traitées polymorphelement comme des objets de leur classe mère grâce au polymorphisme.

## Question 3: Pourquoi utiliser des classes abstraites au lieu d'interfaces?

**Réponse:**
Les classes abstraites permettent de partager:
- Des attributs communs (id, titre, auteur, etc.)
- Une implémentation partielle du code (getters, setters, méthode toString)
- Un constructeur commun

Les interfaces n'auraient pas permis de partager ces attributs et implémentations.

## Question 4: Comment les collections Java sont utilisées?

**Réponse:**
1. **ArrayList<Document>**: Pour stocker la liste ordonnée des documents
2. **ArrayList<Utilisateur>**: Pour stocker la liste des utilisateurs
3. **ArrayList<Emprunt>**: Pour gérer les emprunts en cours
4. **HashSet<String>**: Pour stocker les emails et éviter les doublons

Ces collections offrent des opérations efficaces (ajout, suppression, recherche).

## Question 5: Comment fonctionne la classe générique Gestionnaire<T>?

**Réponse:**
La classe Gestionnaire<T> permet de gérer n'importe quel type d'objet:

```java
Gestionnaire<Document> documents = new Gestionnaire<>(Document::getId);
Gestionnaire<Utilisateur> utilisateurs = new Gestionnaire<>(Utilisateur::getId);
```

Avantages:
- Code réutilisable pour différents types
- Méthodes add(), remove(), getAll(), findById() génériques (l'identifiant est extrait par la fonction passée au constructeur)
- Évite la duplication de code

## Question 6: Quelles sont les exceptions gérées et comment?

**Réponse:**
Les exceptions personnalisées gèrent:
- DocumentIndisponibleException: Document déjà emprunté
- DocumentIntrouvableException: Document non trouvé
- UtilisateurIntrouvableException: Utilisateur non trouvé
- EmpruntImpossibleException: Erreur logique d'emprunt
- EmailDejaExisteException: Email déjà utilisé
- IdDejaExisteException: Identifiant de document ou d'utilisateur déjà utilisé

Chaque exception étend Exception. BibliothequeService les lève (`throws`) et MenuConsole les intercepte avec try/catch pour afficher un message sans interrompre le programme. Les saisies non numériques (`InputMismatchException`) sont aussi interceptées dans MenuConsole.

## Question 7: Comment le contrôle de disponibilité des documents est assuré?

**Réponse:**
Le statut `statut` (enum `Document.Statut`) dans la classe Document:
- Initialisé à `DISPONIBLE` lors de la création
- Mis à `EMPRUNTE` lors de l'emprunt
- Remis à `DISPONIBLE` lors du retour

La méthode `emprunter` vérifie ce statut (via `isDisponible()`) avant de permettre l'emprunt et lève `DocumentIndisponibleException` sinon.