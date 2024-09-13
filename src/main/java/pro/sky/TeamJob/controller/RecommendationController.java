package pro.sky.TeamJob.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.TeamJob.dto.RecommendationDTO;
import pro.sky.TeamJob.model.Rule;
import pro.sky.TeamJob.service.RecommendationService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static pro.sky.TeamJob.utils.Constant.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/recommendations")
public class RecommendationController {

    private RecommendationService recommendationService;

    @GetMapping("/rule")
    public ResponseEntity<RecommendationDTO> getRecommendationForUserIdByRule(@RequestParam(value = "userId") UUID userId,
                                                                              @RequestBody(required = false) Rule rule) {

        if (recommendationService.userMatchesTheRule(userId, rule)) {
            return ResponseEntity.ok(new RecommendationDTO(rule.getProductType(), rule.getRecommendationDescription()));
        }
        return ResponseEntity.ok(new RecommendationDTO("Нет подходящего продукта", "Пользователю не подходит продукт"));
    }

    @GetMapping
    ResponseEntity<List<RecommendationDTO>> getRecommendation(@RequestParam(value = "userId") UUID userId) {

        List<RecommendationDTO> result = new ArrayList<>();

        if (recommendationService.userMatchesTheRule(userId, RULE_INVEST)) {
            result.add(new RecommendationDTO(RULE_INVEST.getProductType(), RULE_INVEST.getRecommendationDescription()));
        }

        if (recommendationService.userMatchesTheRule(userId, RULE_SAVING)) {
            result.add(new RecommendationDTO(RULE_SAVING.getProductType(), RULE_SAVING.getRecommendationDescription()));
        }

        if (recommendationService.userMatchesTheRule(userId, RULE_CREDIT)) {
            result.add(new RecommendationDTO(RULE_SAVING.getProductType(), RULE_SAVING.getRecommendationDescription()));
        }

        if (result.isEmpty()) {
            result.add(new RecommendationDTO("Нет подходящего продукта", "Пользователю не подходит продукт"));
        }
        return ResponseEntity.ok(result);
    }
}
