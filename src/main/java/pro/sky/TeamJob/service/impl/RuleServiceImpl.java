package pro.sky.TeamJob.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.digester.Rule;
import org.springframework.stereotype.Service;
import pro.sky.TeamJob.dto.QueryEntity;
import pro.sky.TeamJob.model.Product;
import pro.sky.TeamJob.model.RuleEntity;
import pro.sky.TeamJob.model.User;
import pro.sky.TeamJob.repository.RuleEntitiesRepository;
import pro.sky.TeamJob.repository.UserRepository;
import pro.sky.TeamJob.service.CacheService;
import pro.sky.TeamJob.service.RuleService;
import pro.sky.TeamJob.utils.RuleStringParserUtils;

import java.util.List;
import java.util.UUID;

/**
 * Сервис описывающий логику создания и проверки пользователя на соответствие правилам
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class RuleServiceImpl implements RuleService {

    /** Репозиторий правил с CRUD опреациями */
    private final RuleEntitiesRepository ruleEntitiesRepository;

    /** Репозиторий пользователей */
    private final UserRepository userRepository;

    /** Работа с кэшем */
    private final CacheService cacheService;

    /**
     * Метод проверки пользователя на соответствие условиям из заданных правил
     * @param rule запрос из правил
     * @param userUUID id пользователя
     * @return true или false
     */
    @Override
    public boolean isRuleCompileAllRequireds(RuleEntity rule, UUID userUUID) {
        String userId = userUUID.toString();
        List<QueryEntity> queryEntities = RuleStringParserUtils.convertRuleStringToQueryEntitesList(rule.getRule());
        boolean isCompileAllRequires = true;
        for (QueryEntity queryEntity: queryEntities) {
            switch (queryEntity.getQuery()) {
                case TOPUP -> isCompileAllRequires = userRepository.findTopup(userId, queryEntity.getArguments()[0], Long.parseLong(queryEntity.getArguments()[1]));
                case USEROF -> isCompileAllRequires = userRepository.findUserOf(userId, queryEntity.getArguments()[0]);
                case NOTUSEROF -> isCompileAllRequires = userRepository.findNotUserOf(userId, queryEntity.getArguments()[0]);
                case TOPUPGTSPEND -> isCompileAllRequires = userRepository.findUsersThenTopUpGTSpend(userId, queryEntity.getArguments()[0]);
                case TOPUPGTSPENDLESS -> isCompileAllRequires = userRepository.findUsersThenTopUpGTSpendLess(userId, queryEntity.getArguments()[0]);
                case SPENDSGT -> isCompileAllRequires = userRepository.findUsersThenSpendSGT(userId, queryEntity.getArguments()[0]) > Long.parseLong(queryEntity.getArguments()[1]);
                case TOPUPSGT -> isCompileAllRequires = userRepository.findUsersThenTopupSGT(userId, queryEntity.getArguments()[0]) > Long.parseLong(queryEntity.getArguments()[1]);
                case ACTIVEUSEROF -> isCompileAllRequires = userRepository.findUsersThenActiveUserOf(userId, queryEntity.getArguments()[0]);
                case ACTIVEUSERANDPAYABLE -> isCompileAllRequires = userRepository.findUsersThenActiveUserOfAndPayable(userId, queryEntity.getArguments()[0], Long.parseLong(queryEntity.getArguments()[1]), Long.parseLong(queryEntity.getArguments()[2]));
            }
            if (!isCompileAllRequires) {
                break;
            }
        }
        return isCompileAllRequires;
    }

    /**
     * Метод сохранения условий из правил в базу данных
     * @param rule запрос из правил
     */
    @Override
    public void createRule(RuleEntity rule) {
        RuleStringParserUtils.isRequestValid(rule.getRule());
        ruleEntitiesRepository.save(rule);
        cacheService.clearCacheOfRecommendation();
    }
}
