package ma.ensate.bibliotheque.exceptions;

public class UtilisateurIntrouvableException extends Exception {
    private static final long serialVersionUID = 1L;

    public UtilisateurIntrouvableException(String message) {
        super(message);
    }
}