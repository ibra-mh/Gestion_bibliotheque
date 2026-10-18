package ma.ensate.bibliotheque.models;

public class Livre extends Document {
    private int nombreDePages;
    private String genre;

    public Livre(String id, String titre, String auteur, int anneePublication, int nombreDePages, String genre) {
        super(id, titre, auteur, anneePublication);
        this.nombreDePages = nombreDePages;
        this.genre = genre;
    }

    public int getNombreDePages() { return nombreDePages; }
    public void setNombreDePages(int nombreDePages) { this.nombreDePages = nombreDePages; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    @Override
    public void afficher() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Livre{" + super.toString() + ", nombreDePages=" + nombreDePages + ", genre='" + genre + "'}";
    }
}
