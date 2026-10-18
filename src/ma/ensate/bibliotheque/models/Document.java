package ma.ensate.bibliotheque.models;

public abstract class Document {
    public enum Statut { DISPONIBLE, EMPRUNTE }

    private String id;
    private String titre;
    private String auteur;
    private int anneePublication;
    private Statut statut;

    public Document(String id, String titre, String auteur, int anneePublication) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.statut = Statut.DISPONIBLE;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }

    public int getAnneePublication() { return anneePublication; }
    public void setAnneePublication(int anneePublication) { this.anneePublication = anneePublication; }

    public Statut getStatut() { return statut; }
    public void setStatut(Statut statut) { this.statut = statut; }

    public boolean isDisponible() { return statut == Statut.DISPONIBLE; }

    public abstract void afficher();

    @Override
    public String toString() {
        return "id='" + id + "', titre='" + titre + "', auteur='" + auteur
                + "', anneePublication=" + anneePublication + ", statut=" + statut;
    }
}
