package pro.sky.TeamJob.service;

import pro.sky.TeamJob.model.RuleEntity;

import java.util.UUID;

/**
 * Интерфейс с методами создания и проверки пользователя на соответствие правилам
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
public interface RuleService {

    boolean isRuleCompileAllRequireds(RuleEntity rule, UUID userUUID);

    void createRule(RuleEntity rule);
}
