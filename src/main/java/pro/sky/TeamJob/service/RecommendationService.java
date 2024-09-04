package pro.sky.TeamJob.service;

import pro.sky.TeamJob.model.Recommendation;

import java.util.List;

public interface RecommendationService {
    List<Recommendation> getRecommendationProduct(String userId);
}
