package pro.sky.TeamJob.exception;

/**
 * Класс исключения. Если переданные имя и фамилия пользователя не были найдены
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
public class UsernameNotExistException extends RuntimeException {
    public UsernameNotExistException() {
        super();
    }

    public UsernameNotExistException(String message) {
        super(message);
    }
}
