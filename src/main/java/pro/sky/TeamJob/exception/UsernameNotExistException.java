package pro.sky.TeamJob.exception;

public class UsernameNotExistException extends RuntimeException {
    public UsernameNotExistException() {
        super();
    }

    public UsernameNotExistException(String message) {
        super(message);
    }
}
