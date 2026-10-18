package ma.ensate.bibliotheque.exceptions;

public class EmailDejaExisteException extends Exception {
    private static final long serialVersionUID = 1L;

    public EmailDejaExisteException(String message) {
        super(message);
    }
}