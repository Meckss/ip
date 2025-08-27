package CustomExceptions;

public class RedundantStateChangeException extends RuntimeException{
    public RedundantStateChangeException(String msg) {
        super(msg);
    }

}
