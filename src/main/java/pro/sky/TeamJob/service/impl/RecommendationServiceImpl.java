package pro.sky.TeamJob.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.TeamJob.model.Product;
import pro.sky.TeamJob.model.Recommendation;
import pro.sky.TeamJob.repository.RecommendationRepository;
import pro.sky.TeamJob.service.RecommendationService;

import java.util.List;

import static pro.sky.TeamJob.utils.TeamJobUtils.*;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;

    @Override
    public List<Recommendation> getRecommendationProduct(String userId) {
        List<Recommendation> result = List.of();

        Product credit = recommendationRepository.recommendedProductCredit(userId);
        Product invest = recommendationRepository.recommendedProductInvest(userId);
        Product saving = recommendationRepository.recommendedProductSaving(userId);

        if (credit != null) {
            result.add(new Recommendation(credit, CREDIT_MESSAGE));
        } else if (invest != null) {
            result.add(new Recommendation(invest, INVEST_MESSAGE));
        } else if (saving != null) {
            result.add(new Recommendation(saving, SAVING_MESSAGE));
        } else {
            return null;
        }

        return result;
    }
}