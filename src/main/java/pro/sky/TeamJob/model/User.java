package pro.sky.TeamJob.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    private String id;
    private LocalDateTime registrationDate;
    private String username;
    private String firstName;
    private String lastName;
    @ManyToMany
    private List<Product> products;
}
