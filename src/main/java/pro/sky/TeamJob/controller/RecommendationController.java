package pro.sky.TeamJob.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.TeamJob.model.Recommendation;
import pro.sky.TeamJob.model.User;
import pro.sky.TeamJob.service.RecommendationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recommendation/")
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationService recommendationService;

    @GetMapping(path = "{userId}")
    public ResponseEntity<List<Recommendation>> getRecommendationProduct(@PathVariable String userId) {
            return ResponseEntity.ok().body(recommendationService.getRecommendationProduct(userId));
    }
}
