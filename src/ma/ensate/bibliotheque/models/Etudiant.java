package ma.ensate.bibliotheque.models;

public class Etudiant extends Utilisateur {
    private String filiere;
    private String niveau;

    public Etudiant(String id, String nom, String email, String filiere, String niveau) {
        super(id, nom, email);
        this.filiere = filiere;
        this.niveau = niveau;
    }

    public String getFiliere() { return filiere; }
    public void setFiliere(String filiere) { this.filiere = filiere; }

    public String getNiveau() { return niveau; }
    public void setNiveau(String niveau) { this.niveau = niveau; }

    @Override
    public void afficher() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Etudiant{" + super.toString() + ", filiere='" + filiere + "', niveau='" + niveau + "'}";
    }
}
