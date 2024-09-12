package pro.sky.TeamJob.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.TeamJob.dto.RecommendationDTO;
import pro.sky.TeamJob.model.Rule;
import pro.sky.TeamJob.service.RecommendationService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RecommendationController {

    private RecommendationService recommendationService;

    @GetMapping("/recommendations")
    public ResponseEntity<RecommendationDTO> getRecommendationForUserId(@RequestParam(value = "userId") UUID userId,
                                                                        @RequestBody(required = false) Rule rule) {

        if (recommendationService.userMatchesTheRule(userId, rule)) {
            return ResponseEntity.ok(new RecommendationDTO(rule.getProductType(), rule.getRecommendationDescription()));
        }
        return ResponseEntity.ok(new RecommendationDTO("Нет подходящего продукта", "Пользователю не подходит продукт"));
    }
}
