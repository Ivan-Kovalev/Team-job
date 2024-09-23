package pro.sky.TeamJob.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс описывающий сущность правила
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = {"description", "rule", "name"})
@Table(name = "rule_entity")
public class RuleEntity {

    /** Id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** само правило в виде строки */
    private String rule;

    /** Имя продукта */
    private String name;

    /** Описание продукта */
    private String description;

}
