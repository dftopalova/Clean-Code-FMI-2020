package custom_exceptions;

public class MalformedRequestException extends IllegalArgumentException {
    public MalformedRequestException(String s) {
        super(s);
    }
}
