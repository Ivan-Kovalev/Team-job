package pro.sky.TeamJob.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.TeamJob.dto.QueriesAndProductType;
import pro.sky.TeamJob.model.Rule;
import pro.sky.TeamJob.repository.RecommendationRepository;
import pro.sky.TeamJob.service.RecommendationService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private RecommendationRepository recommendationRepository;

    public boolean userMatchesTheRule(UUID userId, Rule rule) {
        boolean userMatchesTheRule = true;
        List<QueriesAndProductType> rules = rulesQueriesParse(rule);

        for (QueriesAndProductType queriesAndProductType : rules) {
            switch (queriesAndProductType.getQueries()) {

                case "userOf":
                    if (!recommendationRepository.isUserOf(
                            userId,
                            queriesAndProductType.getProductType())) {
                        userMatchesTheRule = false;
                        break;
                    }

                case "notUserOf":
                    if (!recommendationRepository.isNotUserOf(
                            userId,
                            queriesAndProductType.getProductType())) {
                        userMatchesTheRule = false;
                        break;
                    }

                case "topUp":
                    if (!recommendationRepository.isTopUp(
                            userId,
                            queriesAndProductType.getProductType(),
                            queriesAndProductType.getArgument())) {
                        userMatchesTheRule = false;
                        break;
                    }

                case "topUpGTSpend":
                    if (!recommendationRepository.isTopUpGTSpend(
                            userId,
                            queriesAndProductType.getProductType())) {
                        userMatchesTheRule = false;
                        break;
                    }

                case "spendSGT":
                    if (recommendationRepository.isSpendSGT(
                            userId,
                            queriesAndProductType.getProductType()) < queriesAndProductType.getArgument()) {
                        userMatchesTheRule = false;
                        break;
                    }

                case "topUpSGT":
                    if (recommendationRepository.isTopUpSGT(
                            userId,
                            queriesAndProductType.getProductType()) < queriesAndProductType.getArgument()) {
                        userMatchesTheRule = false;
                        break;
                    }

                case "activeUserOf":
                    if (!recommendationRepository.isActiveUserOf(
                            userId,
                            queriesAndProductType.getProductType())) {
                        userMatchesTheRule = false;
                        break;
                    }
            }
        }
        return userMatchesTheRule;
    }

    private List<QueriesAndProductType> rulesQueriesParse(Rule rule) {

        List<QueriesAndProductType> result = new ArrayList<>();

        String[] parse = rule.getRule().split("%");

        for (String queries : parse) {
            String[] split = queries.split(":");
            result.add(new QueriesAndProductType(split[0], split[1], Long.valueOf(split[2])));
        }

        return result;
    }
}
