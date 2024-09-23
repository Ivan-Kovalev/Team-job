package pro.sky.TeamJob.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = {"description", "rule", "name"})
@Table(name = "rule_entity")
@AllArgsConstructor
@NoArgsConstructor
public class RuleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rule;
    private String name;
    private String description;

}
