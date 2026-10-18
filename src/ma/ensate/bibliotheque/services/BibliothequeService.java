package ma.ensate.bibliotheque.services;

import ma.ensate.bibliotheque.exceptions.DocumentIndisponibleException;
import ma.ensate.bibliotheque.exceptions.DocumentIntrouvableException;
import ma.ensate.bibliotheque.exceptions.EmailDejaExisteException;
import ma.ensate.bibliotheque.exceptions.EmpruntImpossibleException;
import ma.ensate.bibliotheque.exceptions.IdDejaExisteException;
import ma.ensate.bibliotheque.exceptions.UtilisateurIntrouvableException;
import ma.ensate.bibliotheque.models.Document;
import ma.ensate.bibliotheque.models.Emprunt;
import ma.ensate.bibliotheque.models.Utilisateur;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

public class BibliothequeService {
    private final Gestionnaire<Document> documents = new Gestionnaire<>(Document::getId);
    private final Gestionnaire<Utilisateur> utilisateurs = new Gestionnaire<>(Utilisateur::getId);
    private final ArrayList<Emprunt> emprunts = new ArrayList<>();
    private final HashSet<String> emails = new HashSet<>();

    public void ajouterDocument(Document document) throws IdDejaExisteException {
        if (documents.findById(document.getId()).isPresent()) {
            throw new IdDejaExisteException("Un document avec l'identifiant " + document.getId() + " existe déjà");
        }
        documents.add(document);
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) throws IdDejaExisteException, EmailDejaExisteException {
        if (utilisateurs.findById(utilisateur.getId()).isPresent()) {
            throw new IdDejaExisteException("Un utilisateur avec l'identifiant " + utilisateur.getId() + " existe déjà");
        }
        String email = normaliser(utilisateur.getEmail());
        if (!emails.add(email)) {
            throw new EmailDejaExisteException("L'email " + utilisateur.getEmail() + " existe déjà");
        }
        utilisateurs.add(utilisateur);
    }

    public ArrayList<Document> getDocuments() {
        return documents.getAll();
    }

    public ArrayList<Utilisateur> getUtilisateurs() {
        return utilisateurs.getAll();
    }

    public ArrayList<Emprunt> getEmprunts() {
        return new ArrayList<>(emprunts);
    }

    public Document trouverDocument(String id) throws DocumentIntrouvableException {
        return documents.findById(id)
                .orElseThrow(() -> new DocumentIntrouvableException("Document " + id + " introuvable"));
    }

    public Utilisateur trouverUtilisateur(String id) throws UtilisateurIntrouvableException {
        return utilisateurs.findById(id)
                .orElseThrow(() -> new UtilisateurIntrouvableException("Utilisateur " + id + " introuvable"));
    }

    public ArrayList<Document> rechercherDocumentsParTitre(String titre) throws DocumentIntrouvableException {
        String recherche = titre.toLowerCase(Locale.ROOT);
        ArrayList<Document> resultats = new ArrayList<>();
        for (Document d : documents.getAll()) {
            if (d.getTitre().toLowerCase(Locale.ROOT).contains(recherche)) {
                resultats.add(d);
            }
        }
        if (resultats.isEmpty()) {
            throw new DocumentIntrouvableException("Aucun document dont le titre contient '" + titre + "'");
        }
        return resultats;
    }

    public Emprunt emprunter(String idUtilisateur, String idDocument, LocalDate dateRetourPrevue)
            throws UtilisateurIntrouvableException, DocumentIntrouvableException,
            DocumentIndisponibleException, EmpruntImpossibleException {
        Utilisateur utilisateur = trouverUtilisateur(idUtilisateur);
        Document document = trouverDocument(idDocument);

        if (!document.isDisponible()) {
            throw new DocumentIndisponibleException("Le document '" + document.getTitre() + "' est déjà emprunté");
        }
        LocalDate aujourdhui = LocalDate.now();
        if (dateRetourPrevue.isBefore(aujourdhui)) {
            throw new EmpruntImpossibleException("La date de retour prévue (" + dateRetourPrevue + ") est déjà passée");
        }

        document.setStatut(Document.Statut.EMPRUNTE);
        Emprunt emprunt = new Emprunt(utilisateur, document, aujourdhui, dateRetourPrevue);
        emprunts.add(emprunt);
        return emprunt;
    }

    public void retourner(String idDocument) throws DocumentIntrouvableException, EmpruntImpossibleException {
        Document document = trouverDocument(idDocument);
        Emprunt emprunt = emprunts.stream()
                .filter(e -> e.getDocument() == document)
                .findFirst()
                .orElseThrow(() -> new EmpruntImpossibleException(
                        "Le document '" + document.getTitre() + "' n'est pas actuellement emprunté"));
        emprunts.remove(emprunt);
        document.setStatut(Document.Statut.DISPONIBLE);
    }

    public ArrayList<Emprunt> getEmpruntsUtilisateur(String idUtilisateur) throws UtilisateurIntrouvableException {
        Utilisateur utilisateur = trouverUtilisateur(idUtilisateur);
        ArrayList<Emprunt> resultats = new ArrayList<>();
        for (Emprunt e : emprunts) {
            if (e.getUtilisateur() == utilisateur) {
                resultats.add(e);
            }
        }
        return resultats;
    }

    private static String normaliser(String email) {
        return email.trim().toLowerCase(Locale.ROOT);
    }
}
