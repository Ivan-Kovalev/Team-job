package pro.sky.TeamJob.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.TeamJob.dto.QueryEntity;
import pro.sky.TeamJob.model.Product;
import pro.sky.TeamJob.model.RuleEntity;
import pro.sky.TeamJob.model.User;
import pro.sky.TeamJob.repository.RuleEntitiesRepository;
import pro.sky.TeamJob.repository.UserRepository;
import pro.sky.TeamJob.service.RuleService;
import pro.sky.TeamJob.utils.RuleStringParserUtils;

import java.util.ArrayList;
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
                case TopUp:
                    if(userRepository.findUsersThenTotUp().stream().noneMatch(user -> user.getId().equals(userUUID))) {
                        System.out.println("НЕ ПОДХОДИТ ПО TopUp");
                        isCompileAllRequires = false;
                    };
                case UserOf:
                     if(userRepository.findUsersThenUserOf().stream().noneMatch(user -> user.getId().equals(userUUID))) {
                         System.out.println("НЕ ПОДХОДИТ ПО UserOf");
                         isCompileAllRequires = false;
                };
                case SpendSGT:
                     if(userRepository.findUsersThenSpendSGT().stream().noneMatch(user -> user.getId().equals(userUUID))) {
                         System.out.println("НЕ ПОДХОДИТ ПО SpendSGT");
                         isCompileAllRequires = false;
                     }
                case TopupSGT:
                    if(userRepository.findUsersThenTopupSGT().stream().noneMatch(user -> user.getId().equals(userUUID))) {
                        System.out.println("НЕ ПОДХОДИТ ПО TopupSGT");
                        isCompileAllRequires = false;
                    }
                case NotUserOf:
                    if(userRepository.findUsersThenNotUserOf().stream().noneMatch(user -> user.getId().equals(userUUID))){
                        System.out.println("НЕ ПОДХОДИТ ПО NotUserOf");
                        isCompileAllRequires = false;
                    }
                case ActiveUserOf:
                    if(userRepository.findUsersThenActiveUserOf().stream().noneMatch(user -> user.getId().equals(userUUID))){
                        System.out.println("НЕ ПОДХОДИТ ПО ActiveUserOf");
                        isCompileAllRequires = false;
                    }
                case TopUpGTSpend:
                    if(userRepository.findUsersThenTopUpGTSpend().stream().noneMatch(user -> user.getId().equals(userUUID))){
                        System.out.println("НЕ ПОДХОДИТ ПО TopUpGTSpend");
                        isCompileAllRequires = false;
                    }
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
