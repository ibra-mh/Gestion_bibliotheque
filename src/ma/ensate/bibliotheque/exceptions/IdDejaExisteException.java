package ma.ensate.bibliotheque.exceptions;

public class IdDejaExisteException extends Exception {
    private static final long serialVersionUID = 1L;

    public IdDejaExisteException(String message) {
        super(message);
    }
}
