package ma.ensate.bibliotheque.models;

public class Enseignant extends Utilisateur {
    private String departement;
    private String specialite;

    public Enseignant(String id, String nom, String email, String departement, String specialite) {
        super(id, nom, email);
        this.departement = departement;
        this.specialite = specialite;
    }

    public String getDepartement() { return departement; }
    public void setDepartement(String departement) { this.departement = departement; }

    public String getSpecialite() { return specialite; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }

    @Override
    public void afficher() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Enseignant{" + super.toString() + ", departement='" + departement
                + "', specialite='" + specialite + "'}";
    }
}
