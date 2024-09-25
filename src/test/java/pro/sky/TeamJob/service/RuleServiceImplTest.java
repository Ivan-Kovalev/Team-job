package pro.sky.TeamJob.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.TeamJob.model.RuleEntity;
import pro.sky.TeamJob.model.User;
import pro.sky.TeamJob.repository.RuleEntitiesRepository;
import pro.sky.TeamJob.repository.UserRepository;
import pro.sky.TeamJob.service.impl.RuleServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RuleServiceImplTest {

    List<User> users;
    List<RuleEntity> ruleEntities;

    @Mock
    private RuleEntitiesRepository ruleEntitiesRepository;

    @Mock
    private UserRepository userRepository;

    private RuleServiceImpl ruleService;

    @BeforeEach
    public void reposInit() {
        ruleService = new RuleServiceImpl(ruleEntitiesRepository, userRepository);
        users = new ArrayList<>();
        ruleEntities = new ArrayList<>();

        users.add(new User("03b40cc9-051d-4814-80f4-1af43cdb3715", LocalDateTime.of(2024, 1, 8, 12, 31), "adalberto.spencer", "Shay", "Breitenberg"));
        users.add(new User("a54b83b1-c420-4f6b-a6cb-53db48cb3886", LocalDateTime.of(2024, 4, 24, 16, 18), "alberta.bergstrom", "Germaine", "Buckridge"));

        ruleEntities.add(new RuleEntity(1L,"TOPUP:DEBIT:100%TOPUPGTSPEND:DEBIT", "START_CREDIT", "Кредит для начинающих"));
        ruleEntities.add(new RuleEntity(2L, "USEROF:CREDIT", "ONE_MORE_CREDIT", "Кредит для продолжащих."));
    }

    @Test
    public void isRuleCompileAllRequiredsIsTrue() {
        User testUser = users.get(0);
        RuleEntity testRuleEntity = ruleEntities.get(1);
        when(userRepository.findUserOf(testUser.getId(), "CREDIT")).thenReturn(true);
        Assertions.assertTrue(ruleService.isRuleCompileAllRequireds(testRuleEntity, UUID.fromString(testUser.getId())));
    }

    @Test
    public void isRuleCompileAllRequiredsIsFalse() {
        User testUser = users.get(0);
        RuleEntity testRuleEntity = ruleEntities.get(1);
        when(userRepository.findUserOf(testUser.getId(), "CREDIT")).thenReturn(false);
        Assertions.assertFalse(ruleService.isRuleCompileAllRequireds(testRuleEntity, UUID.fromString(testUser.getId())));
    }

}
