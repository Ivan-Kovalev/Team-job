package pro.sky.TeamJob.service;

import pro.sky.TeamJob.dto.Recommendation;
import pro.sky.TeamJob.model.User;

import java.util.List;


public interface RecommendationService {
    List<Recommendation> getRecommendationProduct(String userId);
    User findUserIdByUsername(String username);
    String getUserIdByUsername(String username);
    String getFirstnameAndLastnameByUsername(String username);
    void clearCacheOfRecommendation();
}