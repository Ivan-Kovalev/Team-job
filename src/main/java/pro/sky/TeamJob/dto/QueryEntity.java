package pro.sky.TeamJob.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс описывающий запрос и аргументы взятые из правила переданного пользователем
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QueryEntity {

    /** Запрос содержащий одну из констант класса Queries */
    private Queries query;

    /** Аргументы запроса */
    private String[] arguments;
}
