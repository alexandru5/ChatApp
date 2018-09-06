package chatapp.exceptions;

public class NoUserWithActivationTokenException extends Exception {
    public NoUserWithActivationTokenException(){}
    public NoUserWithActivationTokenException(String message) { super(message); }
    public NoUserWithActivationTokenException(String message, Throwable cause) { super(message, cause); }
    public NoUserWithActivationTokenException(Throwable cause) { super(cause); }
}
