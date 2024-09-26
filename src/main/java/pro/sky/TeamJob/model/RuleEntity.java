package pro.sky.TeamJob.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

/**
 * Класс описывающий сущность правила
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
@Schema(description = "Сущность правила")
@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = {"description", "rule", "name"})
@Table(name = "rule_entity")
@AllArgsConstructor
@NoArgsConstructor
public class RuleEntity {

    /** Id */
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** само правило в виде строки */
    @Schema(description = "Правило в виде строки", example = "запрос:АРГУМЕНТ%запрос:АРГУМЕНТ%запрос:АРГУМЕНТ")
    private String rule;

    /** Имя продукта */
    @Schema(description = "Имя продукта")
    private String name;

    /** Описание продукта */
    @Schema(description = "Описание продукта")
    private String description;

}
