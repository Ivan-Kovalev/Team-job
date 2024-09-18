package pro.sky.TeamJob.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pro.sky.TeamJob.dto.Recommendation;
import pro.sky.TeamJob.exception.UsernameNotExistException;
import pro.sky.TeamJob.model.RuleEntity;
import pro.sky.TeamJob.model.User;
import pro.sky.TeamJob.repository.RuleEntitiesRepository;
import pro.sky.TeamJob.repository.UserRepository;
import pro.sky.TeamJob.service.RecommendationService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final RuleEntitiesRepository ruleEntitiesRepository;
    private final RuleServiceImpl ruleService;
    private final UserRepository userRepository;
    private final CacheManager cacheManager;

    @Override
    @Cacheable("getRecommendation")
    public List<Recommendation> getRecommendationProduct(String userId) {
        List<RuleEntity> ruleEntities = ruleEntitiesRepository.findAll();
        List<Recommendation> recommendations = new ArrayList<>();
        if (ruleEntities.isEmpty()) {
            return recommendations;
        }
        for (RuleEntity rule: ruleEntities) {
            if (ruleService.isRuleCompileAllRequireds(rule, userId)) {
                recommendations.add(new Recommendation(rule.getName(), rule.getDescription()));
            }
        }
        return recommendations;
    }

    public User findUserIdByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotExistException("Ошибка! Пользователя с указанным username не существует."));
    }

    public String getUserIdByUsername(String username) {
        return findUserIdByUsername(username).getUsername();
    }

    public String getFirstnameAndLastnameByUsername(String username) {
        User user = findUserIdByUsername(username);
        return user.getFirstname() + " " + user.getLastname();
    }

    @Override
    public void clearCacheOfRecommendation() {
        cacheManager.getCache("getRecommendation").clear();
    }

}