package ma.ensate.bibliotheque.app;

import ma.ensate.bibliotheque.exceptions.DocumentIndisponibleException;
import ma.ensate.bibliotheque.exceptions.DocumentIntrouvableException;
import ma.ensate.bibliotheque.exceptions.EmailDejaExisteException;
import ma.ensate.bibliotheque.exceptions.EmpruntImpossibleException;
import ma.ensate.bibliotheque.exceptions.IdDejaExisteException;
import ma.ensate.bibliotheque.exceptions.UtilisateurIntrouvableException;
import ma.ensate.bibliotheque.models.Document;
import ma.ensate.bibliotheque.models.Emprunt;
import ma.ensate.bibliotheque.models.Enseignant;
import ma.ensate.bibliotheque.models.Etudiant;
import ma.ensate.bibliotheque.models.Livre;
import ma.ensate.bibliotheque.models.Magazine;
import ma.ensate.bibliotheque.models.Memoire;
import ma.ensate.bibliotheque.models.Utilisateur;
import ma.ensate.bibliotheque.services.BibliothequeService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MenuConsole {
    private static final int QUITTER = 10;

    private final BibliothequeService service = new BibliothequeService();
    private final Scanner scanner = new Scanner(System.in);

    public void demarrer() {
        int choix = 0;
        try {
            while (choix != QUITTER) {
                afficherMenu();
                try {
                    choix = lireEntier();
                    traiterChoix(choix);
                } catch (InputMismatchException e) {
                    choix = 0;
                    System.out.println("Saisie invalide : un nombre entier est attendu.");
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("\nFin de la saisie.");
        } finally {
            scanner.close();
        }
    }

    private void afficherMenu() {
        System.out.println("\n===== Gestion Bibliothèque =====");
        System.out.println("1. Ajouter un document");
        System.out.println("2. Ajouter un utilisateur");
        System.out.println("3. Afficher tous les documents");
        System.out.println("4. Afficher tous les utilisateurs");
        System.out.println("5. Rechercher un document (par identifiant ou titre)");
        System.out.println("6. Emprunter un document");
        System.out.println("7. Retourner un document");
        System.out.println("8. Afficher les emprunts d'un utilisateur");
        System.out.println("9. Afficher tous les emprunts en cours");
        System.out.println("10. Quitter");
        System.out.print("Votre choix : ");
    }

    private void traiterChoix(int choix) {
        try {
            switch (choix) {
                case 1 -> ajouterDocument();
                case 2 -> ajouterUtilisateur();
                case 3 -> afficherDocuments();
                case 4 -> afficherUtilisateurs();
                case 5 -> rechercherDocument();
                case 6 -> emprunterDocument();
                case 7 -> retournerDocument();
                case 8 -> afficherEmpruntsUtilisateur();
                case 9 -> afficherTousEmprunts();
                case QUITTER -> System.out.println("Au revoir !");
                default -> System.out.println("Choix invalide : saisir un nombre entre 1 et 10.");
            }
        } catch (IdDejaExisteException | EmailDejaExisteException | DocumentIntrouvableException
                 | UtilisateurIntrouvableException | DocumentIndisponibleException | EmpruntImpossibleException
                 | IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Erreur : format de date invalide (attendu : yyyy-MM-dd).");
        }
    }

    private void ajouterDocument() throws IdDejaExisteException {
        System.out.println("\n--- Type de document ---");
        System.out.println("1. Livre");
        System.out.println("2. Magazine");
        System.out.println("3. Mémoire");
        int type = lireEntier("Votre choix : ");
        if (type < 1 || type > 3) {
            System.out.println("Type invalide.");
            return;
        }

        String id = lireTexte("Identifiant : ");
        String titre = lireTexte("Titre : ");
        String auteur = lireTexte("Auteur : ");
        int annee = lireEntier("Année de publication : ");

        Document document;
        switch (type) {
            case 1 -> {
                int pages = lireEntier("Nombre de pages : ");
                String genre = lireTexte("Genre : ");
                document = new Livre(id, titre, auteur, annee, pages, genre);
            }
            case 2 -> {
                int numero = lireEntier("Numéro d'édition : ");
                String mois = lireTexte("Mois de publication : ");
                document = new Magazine(id, titre, auteur, annee, numero, mois);
            }
            default -> {
                Memoire.Niveau niveau = lireNiveauMemoire();
                String encadrant = lireTexte("Encadrant : ");
                document = new Memoire(id, titre, auteur, annee, niveau, encadrant);
            }
        }
        service.ajouterDocument(document);
        System.out.println("Document ajouté avec succès.");
    }

    private void ajouterUtilisateur() throws IdDejaExisteException, EmailDejaExisteException {
        System.out.println("\n--- Type d'utilisateur ---");
        System.out.println("1. Étudiant");
        System.out.println("2. Enseignant");
        int type = lireEntier("Votre choix : ");
        if (type < 1 || type > 2) {
            System.out.println("Type invalide.");
            return;
        }

        String id = lireTexte("Identifiant : ");
        String nom = lireTexte("Nom : ");
        String email = lireTexte("Email : ");

        Utilisateur utilisateur;
        if (type == 1) {
            String filiere = lireTexte("Filière : ");
            String niveau = lireTexte("Niveau : ");
            utilisateur = new Etudiant(id, nom, email, filiere, niveau);
        } else {
            String departement = lireTexte("Département : ");
            String specialite = lireTexte("Spécialité : ");
            utilisateur = new Enseignant(id, nom, email, departement, specialite);
        }
        service.ajouterUtilisateur(utilisateur);
        System.out.println("Utilisateur ajouté avec succès.");
    }

    private void afficherDocuments() {
        System.out.println("\n--- Documents ---");
        List<Document> documents = service.getDocuments();
        if (documents.isEmpty()) {
            System.out.println("Aucun document.");
        }
        documents.forEach(Document::afficher);
    }

    private void afficherUtilisateurs() {
        System.out.println("\n--- Utilisateurs ---");
        List<Utilisateur> utilisateurs = service.getUtilisateurs();
        if (utilisateurs.isEmpty()) {
            System.out.println("Aucun utilisateur.");
        }
        utilisateurs.forEach(Utilisateur::afficher);
    }

    private void rechercherDocument() throws DocumentIntrouvableException {
        System.out.println("\n--- Rechercher un document ---");
        System.out.println("1. Par identifiant");
        System.out.println("2. Par titre");
        int type = lireEntier("Votre choix : ");

        switch (type) {
            case 1 -> service.trouverDocument(lireTexte("Identifiant : ")).afficher();
            case 2 -> service.rechercherDocumentsParTitre(lireTexte("Titre : ")).forEach(Document::afficher);
            default -> System.out.println("Choix invalide.");
        }
    }

    private void emprunterDocument() throws UtilisateurIntrouvableException, DocumentIntrouvableException,
            DocumentIndisponibleException, EmpruntImpossibleException {
        String idUtilisateur = lireTexte("Identifiant utilisateur : ");
        String idDocument = lireTexte("Identifiant document : ");
        LocalDate dateRetour = LocalDate.parse(lireTexte("Date de retour prévue (yyyy-MM-dd) : "));

        Emprunt emprunt = service.emprunter(idUtilisateur, idDocument, dateRetour);
        System.out.println("Document emprunté avec succès : " + emprunt);
    }

    private void retournerDocument() throws DocumentIntrouvableException, EmpruntImpossibleException {
        service.retourner(lireTexte("Identifiant document : "));
        System.out.println("Document retourné avec succès.");
    }

    private void afficherEmpruntsUtilisateur() throws UtilisateurIntrouvableException {
        List<Emprunt> emprunts = service.getEmpruntsUtilisateur(lireTexte("Identifiant utilisateur : "));
        if (emprunts.isEmpty()) {
            System.out.println("Aucun emprunt en cours pour cet utilisateur.");
        }
        emprunts.forEach(System.out::println);
    }

    private void afficherTousEmprunts() {
        System.out.println("\n--- Emprunts en cours ---");
        List<Emprunt> emprunts = service.getEmprunts();
        if (emprunts.isEmpty()) {
            System.out.println("Aucun emprunt en cours.");
        }
        emprunts.forEach(System.out::println);
    }

    private Memoire.Niveau lireNiveauMemoire() {
        String saisie = lireTexte("Niveau (Licence/Master/Doctorat) : ");
        try {
            return Memoire.Niveau.valueOf(saisie.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Niveau invalide : '" + saisie + "' (Licence, Master ou Doctorat attendu)");
        }
    }

    private String lireTexte(String invite) {
        System.out.print(invite);
        return scanner.nextLine().trim();
    }

    private int lireEntier(String invite) {
        System.out.print(invite);
        return lireEntier();
    }

    // Consomme toujours le reste de la ligne, y compris après un jeton invalide.
    private int lireEntier() {
        try {
            return scanner.nextInt();
        } finally {
            scanner.nextLine();
        }
    }
}
