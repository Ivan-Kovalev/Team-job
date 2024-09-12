package pro.sky.TeamJob.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.TeamJob.dto.RecommendationDTO;
import pro.sky.TeamJob.model.Rule;
import pro.sky.TeamJob.service.RecommendationService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/recommendation")
public class RecommendationController {

    private RecommendationService recommendationService;

    @GetMapping()
    public RecommendationDTO getRecommendationForUserId(@RequestParam UUID userId, @RequestParam Rule rule) {
        if (recommendationService.userMatchesTheRule(userId, rule)) {
            return new RecommendationDTO(rule.getProductType(), rule.getRecommendationDescription());
        }
        return new RecommendationDTO("Нет продукта", "Пользователю не подходит продукт");
    }
}
