package pro.sky.TeamJob.exception;

/**
 * Класс исключения. Если переданный ID не соответствует форме UUID
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
public class UuidIsNotValidException extends RuntimeException {

    public UuidIsNotValidException() {
    }

    public UuidIsNotValidException(String message) {
        super(message);
    }
}
