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
import pro.sky.TeamJob.service.RuleService;
import pro.sky.TeamJob.utils.RuleStringParserUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RuleServiceImpl implements RuleService {

    private final RuleEntitiesRepository ruleEntitiesRepository;
    private final UserRepository userRepository;

    @Override
    public boolean isRuleCompileAllRequireds(RuleEntity rule, String userUUID) {
        List<QueryEntity> queryEntities = RuleStringParserUtils.convertRuleStringToQueryEntitesList(rule.getRule());
        boolean isCompileAllRequires = true;
        for (QueryEntity queryEntity: queryEntities) {
            switch (queryEntity.getQuery()) {
                case TOPUP -> isCompileAllRequires = userRepository.findTopup(userUUID, queryEntity.getArguments()[0], Long.getLong(queryEntity.getArguments()[1]));
                case USEROF -> isCompileAllRequires = userRepository.findUserOf(userUUID, queryEntity.getArguments()[0]);
                case NOTUSEROF -> isCompileAllRequires = userRepository.findNotUserOf(userUUID, queryEntity.getArguments()[0]);
                case TOPUPGTSPEND -> isCompileAllRequires = userRepository.findUsersThenTopUpGTSpend(userUUID, queryEntity.getArguments()[0]);
                case SPENDSGT -> isCompileAllRequires = userRepository.findUsersThenSpendSGT(userUUID, queryEntity.getArguments()[0]) > Long.getLong(queryEntity.getArguments()[1]);
                case TOPUPSGT -> isCompileAllRequires = userRepository.findUsersThenTopupSGT(userUUID, queryEntity.getArguments()[0]) > Long.getLong(queryEntity.getArguments()[1]);
                case ACTIVEUSEROF -> isCompileAllRequires = userRepository.findUsersThenActiveUserOf(userUUID, queryEntity.getArguments()[0]);
            }
            if (!isCompileAllRequires) {
                break;
            }
        }
        return isCompileAllRequires;
    }

    @Override
    public boolean isRulesNameValid(Product product) {
        return false;
    }

    @Override
    public void createRule(RuleEntity rule) {
        RuleStringParserUtils.isRequestValid(rule.getRule());
        ruleEntitiesRepository.save(rule);
    }
}
