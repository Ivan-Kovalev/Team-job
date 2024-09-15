package pro.sky.TeamJob.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.TeamJob.dto.Recommendation;
import pro.sky.TeamJob.exception.UuidIsNotValidException;
import pro.sky.TeamJob.model.RuleEntity;
import pro.sky.TeamJob.service.RecommendationService;
import pro.sky.TeamJob.utils.Validator;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recommendation/")
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationService recommendationService;

    @GetMapping(path = "{userId}")
    public ResponseEntity<List<Recommendation>> getRecommendationProduct(@PathVariable String userId) {
        if(!Validator.isValidUUID(userId)) {
           throw new UuidIsNotValidException("Ошибка! Невалидный ID пользователя!");
        }
        return ResponseEntity.ok().body(recommendationService.getRecommendationProduct(userId));
    }

    @ExceptionHandler(UuidIsNotValidException.class)
    private ResponseEntity<String> handler(UuidIsNotValidException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
