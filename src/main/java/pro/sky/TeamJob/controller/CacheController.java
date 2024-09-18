package pro.sky.TeamJob.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.TeamJob.service.RecommendationService;


@RestController
@RequestMapping("/management/")
@RequiredArgsConstructor
public class CacheController {

    private final RecommendationService recommendationService;

    @GetMapping("clear-caches/getRecommendation")
    public ResponseEntity<String> clearCacheOfRecommendation() {
        recommendationService.clearCacheOfRecommendation();
        return ResponseEntity.ok().body("Кэш очищен");
    }
}
