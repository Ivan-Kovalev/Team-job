package pro.sky.TeamJob.service;

import pro.sky.TeamJob.model.Rule;

import java.util.UUID;

public interface RecommendationService {
    boolean userMatchesTheRule(UUID userId, Rule rule);
}