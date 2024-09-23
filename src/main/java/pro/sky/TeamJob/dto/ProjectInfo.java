package pro.sky.TeamJob.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс описывающий информации о проекте
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectInfo {

    /** Имя проекта */
    private String name;

    /** Версия проекта */
    private String version;

}
