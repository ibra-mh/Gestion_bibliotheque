package ma.ensate.bibliotheque.models;

public class Memoire extends Document {
    public enum Niveau { LICENCE, MASTER, DOCTORAT }

    private Niveau niveau;
    private String encadrant;

    public Memoire(String id, String titre, String auteur, int anneePublication, Niveau niveau, String encadrant) {
        super(id, titre, auteur, anneePublication);
        this.niveau = niveau;
        this.encadrant = encadrant;
    }

    public Niveau getNiveau() { return niveau; }
    public void setNiveau(Niveau niveau) { this.niveau = niveau; }

    public String getEncadrant() { return encadrant; }
    public void setEncadrant(String encadrant) { this.encadrant = encadrant; }

    @Override
    public void afficher() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Memoire{" + super.toString() + ", niveau=" + niveau + ", encadrant='" + encadrant + "'}";
    }
}
