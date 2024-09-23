package pro.sky.TeamJob.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Класс описывающий пользователя
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userbase")
public class User {

    /** ID */
    @Id
    private String id;

    /** Дата регистрации */
    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    /** логи пользователя */
    private String username;

    /** Имя */
    private String firstname;

    /** Фамилия */
    private String lastname;
}
