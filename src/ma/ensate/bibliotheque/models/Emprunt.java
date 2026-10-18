package ma.ensate.bibliotheque.models;

import java.time.LocalDate;

public class Emprunt {
    private Utilisateur utilisateur;
    private Document document;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;

    public Emprunt(Utilisateur utilisateur, Document document, LocalDate dateEmprunt, LocalDate dateRetourPrevue) {
        this.utilisateur = utilisateur;
        this.document = document;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
    }

    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }

    public Document getDocument() { return document; }
    public void setDocument(Document document) { this.document = document; }

    public LocalDate getDateEmprunt() { return dateEmprunt; }
    public void setDateEmprunt(LocalDate dateEmprunt) { this.dateEmprunt = dateEmprunt; }

    public LocalDate getDateRetourPrevue() { return dateRetourPrevue; }
    public void setDateRetourPrevue(LocalDate dateRetourPrevue) { this.dateRetourPrevue = dateRetourPrevue; }

    @Override
    public String toString() {
        return "Emprunt{utilisateur=" + utilisateur.getNom() + ", document=" + document.getTitre()
                + ", dateEmprunt=" + dateEmprunt + ", dateRetourPrevue=" + dateRetourPrevue + "}";
    }
}
