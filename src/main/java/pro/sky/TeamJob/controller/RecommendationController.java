package pro.sky.TeamJob.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.TeamJob.dto.Recommendation;
import pro.sky.TeamJob.exception.UuidIsNotValidException;
import pro.sky.TeamJob.service.RecommendationService;

import java.util.List;
import java.util.UUID;

/**
 * Класс контроллер рекомендаций
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
@Tag(name="Контроллер рекомендаций", description="Предоставляет рекомендации для пользователей")
@RestController
@RequestMapping("/api/v1/recommendation/")
@RequiredArgsConstructor
public class RecommendationController {

    /** Сервис рекомендаций с бизнес логикой */
    private final RecommendationService recommendationService;

    /**
     * Метод предоставления рекомендации по ID пользователя
     * @param userId id пользователя
     * @return статус выполнения с рекомендациями для пользователя
     */
    @Operation(
            summary = "Получить рекомендацию",
            description = "Позволяет получить рекомендацию продукта пользователю подходящего по условиям из правил"
    )
    @GetMapping(path = "{userId}")
    public ResponseEntity<List<Recommendation>> getRecommendationProduct(@PathVariable @Parameter(description = "ID пользователя") UUID userId) {
        return ResponseEntity.ok().body(recommendationService.getRecommendationProduct(userId));
    }

    /**
     * Метод обработки ошибки при неверной передачи id
     * @param e тип ошибки
     * @return статус с сообщением
     */
    @ExceptionHandler(UuidIsNotValidException.class)
    private ResponseEntity<String> handler(UuidIsNotValidException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
