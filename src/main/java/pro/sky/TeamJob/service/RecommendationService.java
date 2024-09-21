package pro.sky.TeamJob.service;

import pro.sky.TeamJob.dto.Recommendation;
import pro.sky.TeamJob.model.User;

import java.util.List;
import java.util.UUID;


public interface RecommendationService {
    List<Recommendation> getRecommendationProduct(UUID userId);
    User findUserIdByUsername(String username);
    String getUserIdByUsername(String username);
    String getFirstnameAndLastnameByUsername(String username);
    void clearCacheOfRecommendation();
}