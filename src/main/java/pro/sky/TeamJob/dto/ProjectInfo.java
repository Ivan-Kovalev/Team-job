package pro.sky.TeamJob.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс описывающий информации о проекте
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
@Schema(description = "Сущность информации о проекте (название и версия)")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectInfo {

    /** Имя проекта */
    @Schema(description = "Название проекта")
    private String name;

    /** Версия проекта */
    @Schema(description = "Версия проекта")
    private String version;

}
