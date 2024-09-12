package pro.sky.TeamJob.service;

import pro.sky.TeamJob.model.Rule;

public interface RuleService {
    Rule add(Rule rule);

    Rule getRule(Long ruleId);

    void delete(Long ruleId);
}
