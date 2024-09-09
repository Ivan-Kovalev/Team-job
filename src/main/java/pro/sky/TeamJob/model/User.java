package pro.sky.TeamJob.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userbase")
public class User {

    @Id
    private String id;
    @Column(name = "registration_date")
    private LocalDateTime registrationDate;
    private String username;
    private String firstname;
    private String lastname;
}
