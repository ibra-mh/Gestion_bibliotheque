package ma.ensate.bibliotheque.models;

public class Magazine extends Document {
    private int numeroEdition;
    private String moisPublication;

    public Magazine(String id, String titre, String auteur, int anneePublication, int numeroEdition, String moisPublication) {
        super(id, titre, auteur, anneePublication);
        this.numeroEdition = numeroEdition;
        this.moisPublication = moisPublication;
    }

    public int getNumeroEdition() { return numeroEdition; }
    public void setNumeroEdition(int numeroEdition) { this.numeroEdition = numeroEdition; }

    public String getMoisPublication() { return moisPublication; }
    public void setMoisPublication(String moisPublication) { this.moisPublication = moisPublication; }

    @Override
    public void afficher() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Magazine{" + super.toString() + ", numeroEdition=" + numeroEdition
                + ", moisPublication='" + moisPublication + "'}";
    }
}
