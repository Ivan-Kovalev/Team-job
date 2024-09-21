package pro.sky.TeamJob.service;

import pro.sky.TeamJob.model.RuleEntity;

import java.util.UUID;

public interface RuleService {

    boolean isRuleCompileAllRequireds(RuleEntity rule, UUID userUUID);

    void createRule(RuleEntity rule);
}
