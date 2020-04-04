package custom_exceptions;

public class UnsupportedPieceTypeException extends UnsupportedOperationException {
    public UnsupportedPieceTypeException(String message) {
        super(message);
    }
}
