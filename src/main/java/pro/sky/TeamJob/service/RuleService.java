package pro.sky.TeamJob.service;

import pro.sky.TeamJob.model.Product;
import pro.sky.TeamJob.model.RuleEntity;

public interface RuleService {

    boolean isRuleCompileAllRequireds(RuleEntity rule, String userUUID);
    boolean isRulesNameValid(Product product);

    void createRule(RuleEntity rule);
}
