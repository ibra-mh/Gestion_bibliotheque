package ma.ensate.bibliotheque.exceptions;

public class DocumentIndisponibleException extends Exception {
    private static final long serialVersionUID = 1L;

    public DocumentIndisponibleException(String message) {
        super(message);
    }
}