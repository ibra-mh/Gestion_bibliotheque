package ma.ensate.bibliotheque.exceptions;

public class EmpruntImpossibleException extends Exception {
    private static final long serialVersionUID = 1L;

    public EmpruntImpossibleException(String message) {
        super(message);
    }
}