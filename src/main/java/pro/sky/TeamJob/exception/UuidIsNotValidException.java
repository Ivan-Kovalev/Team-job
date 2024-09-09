package pro.sky.TeamJob.exception;

public class UuidIsNotValidException extends RuntimeException {

    public UuidIsNotValidException() {
    }

    public UuidIsNotValidException(String message) {
        super(message);
    }
}
