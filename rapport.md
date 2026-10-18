#  Projet Java Gestion_bibliotheque

## 1. Présentation du projet

Ce projet a pour objectif de développer une application Java de gestion d'une bibliothèque universitaire. Il permet de gérer les documents (livres, magazines, mémoires), les utilisateurs (étudiants, enseignants), ainsi que les emprunts et retours de documents via une interface console.

## 2. Objectifs de l'application

- Permettre la gestion complète des documents de la bibliothèque
- Permettre la gestion des utilisateurs
- Gérer les emprunts avec vérification de disponibilité
- Fournir une interface console simple et fonctionnelle
- Appliquer les concepts de programmation orientée objet

## 3. Diagramme de classes

Le diagramme de classes montre:
- Les classes abstraites Document et Utilisateur
- Les héritages vers les classes filles
- Les associations entre Emprunt, Document et Utilisateur
- Les méthodes et attributs de chaque classe

## 4. Diagramme de cas d'utilisation

Acteurs:
- Bibliothécaire: peut ajouter/supprimer des documents et utilisateurs, afficher les listes, rechercher
- Utilisateur: peut emprunter, retourner et consulter ses emprunts

## 5. Explication des principales classes

**Document (abstraite)**: Base pour tous les types de documents avec attributs communs.

**Livre/Magazine/Memoire**: Spécialisation avec attributs spécifiques à chaque type.

**Utilisateur (abstraite)**: Base pour les types d'utilisateurs.

**Etudiant/Enseignant**: Spécialisation avec attributs spécifiques.

**Emprunt**: Représente une opération d'emprunt avec liens vers Utilisateur et Document.

**Gestionnaire<T>**: Classe générique pour gérer les collections d'objets.

**BibliothequeService**: Logique métier centralisée.

## 6. Collections utilisées

- ArrayList pour les listes ordonnées et indexables
- HashSet pour l'unicité des emails

## 7. Exceptions utilisées

Gestion des cas d'erreur: documents non trouvés, indisponibles, emails dupliqués.

## 8. Conclusion

Projet réalisé selon les principes OOP avec organisation en packages, compilation réussie.
