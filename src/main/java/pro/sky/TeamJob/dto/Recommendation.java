package pro.sky.TeamJob.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Objects;

/**
 * Класс описывающий рекомендации для передачи пользователю
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Recommendation {

    /** Имя продукта */
    private String name;

    /** Текст рекомендации продукта */
    private String text;

    /** Метод преобразования рекомендации в строковый вид для передачи пользователю */
    @Override
    public String toString() {
        return "Рекомендация: " + name + "\n"
                + text;
    }

}
