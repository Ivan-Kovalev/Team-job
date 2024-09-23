package pro.sky.TeamJob.controller;

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
    @GetMapping(path = "{userId}")
    public ResponseEntity<List<Recommendation>> getRecommendationProduct(@PathVariable UUID userId) {
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
