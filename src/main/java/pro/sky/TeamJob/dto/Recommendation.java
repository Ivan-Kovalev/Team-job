package pro.sky.TeamJob.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс описывающий рекомендации для передачи пользователю
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
@Schema(description = "Сущность рекомендации для пользователя")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Recommendation {

    /** Имя продукта */
    @Schema(description = "Имя продукта")
    private String name;

    /** Текст рекомендации продукта */
    @Schema(description = "Текст рекомендации")
    private String text;

    /** Метод преобразования рекомендации в строковый вид для передачи пользователю */
    @Override
    public String toString() {
        return "Рекомендация: " + name + "\n"
                + text;
    }

}
