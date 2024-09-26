package pro.sky.TeamJob.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.TeamJob.dto.ProjectInfo;

/**
 * Класс контроллер информации о проекте
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
@Tag(name="Контроллер информации о проекте", description="Предоставляет информацию о поекте")
@RestController
@RequestMapping("/api/v1/management/")
@RequiredArgsConstructor
public class InformController {

    /** Класс передающий информацию о проекте */
    private final BuildProperties buildProperties;

    /**
     * Метод передающий информацию о проекте (имя проекта и его версия)
     * @return результат с информацией о проекте
     */
    @Operation(
            summary = "Информация о проекте",
            description = "Позволяет получить информацию о проекте, его название и версию!"
    )
    @GetMapping("info/")
    private ResponseEntity<ProjectInfo> getInfoAboutProject() {
        return ResponseEntity.ok().body(new ProjectInfo(buildProperties.getName(), buildProperties.getVersion()));
    }

}
