package pro.sky.TeamJob.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.TeamJob.dto.Recommendation;
import pro.sky.TeamJob.model.RuleEntity;
import pro.sky.TeamJob.repository.RuleEntitiesRepository;
import pro.sky.TeamJob.service.RecommendationService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final RuleEntitiesRepository ruleEntitiesRepository;
    private final RuleServiceImpl ruleService;

    @Override
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


}