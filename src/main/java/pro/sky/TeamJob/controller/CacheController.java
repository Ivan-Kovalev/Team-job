package pro.sky.TeamJob.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.TeamJob.service.RecommendationService;

/**
 * Класс контроллер кэша
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
@Tag(name="Контроллер кэша", description="Осуществляет все операции связанные с кэшем")
@RestController
@RequestMapping("/management/")
@RequiredArgsConstructor
public class CacheController {

    /** Сервис рекомендаций */
    private final RecommendationService recommendationService;

    /** Метод очищения кэша рекомендаций */
    @Operation(
            summary = "Очищение кэша",
            description = "Позволяет очистить кэш приложения"
    )
    @GetMapping("clear-caches/getRecommendation")
    public ResponseEntity<String> clearCacheOfRecommendation() {
        recommendationService.clearCacheOfRecommendation();
        return ResponseEntity.ok().body("Кэш очищен");
    }
}
