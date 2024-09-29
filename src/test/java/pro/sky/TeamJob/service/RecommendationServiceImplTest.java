package pro.sky.TeamJob.service;

import org.apache.tomcat.util.digester.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import pro.sky.TeamJob.dto.Recommendation;
import pro.sky.TeamJob.exception.UsernameNotExistException;
import pro.sky.TeamJob.model.RuleEntity;
import pro.sky.TeamJob.model.User;
import pro.sky.TeamJob.repository.RuleEntitiesRepository;
import pro.sky.TeamJob.repository.UserRepository;
import pro.sky.TeamJob.service.impl.RecommendationServiceImpl;
import pro.sky.TeamJob.service.impl.RuleServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecommendationServiceImplTest {

    List<User> users;
    List<RuleEntity> ruleEntities;
    List<Recommendation> recommendations;

    @Mock
    RuleEntitiesRepository ruleEntitiesRepository;

    @Mock
    RuleServiceImpl ruleService;

    @Mock
    UserRepository userRepository;

    @Mock
    CacheManager cacheManager;

    RecommendationServiceImpl recommendationService;

    @BeforeEach
    public void reposInit() {
        recommendationService = new RecommendationServiceImpl(ruleEntitiesRepository, ruleService, userRepository);
        users = new ArrayList<>();
        ruleEntities = new ArrayList<>();
        recommendations = new ArrayList<>();

        users.add(new User("03b40cc9-051d-4814-80f4-1af43cdb3715", LocalDateTime.of(2024, 1, 8, 12, 31), "adalberto.spencer", "Shay", "Breitenberg"));
        users.add(new User("a54b83b1-c420-4f6b-a6cb-53db48cb3886", LocalDateTime.of(2024, 4, 24, 16, 18), "alberta.bergstrom", "Germaine", "Buckridge"));
        users.add(new User("b1c67527-82bd-4a1f-96f2-910bd486f982", LocalDateTime.of(2024, 2, 28, 21, 0), "alden.dooley", "Janis", "Kub"));
        users.add(new User("f14ccefb-37fa-4ee0-a384-9be486c71e06", LocalDateTime.of(2024, 12, 8, 8, 3), "anthony.carter", "Lessie", "Bogisich"));
        users.add(new User("f062242b-308b-45ca-b8f8-cdbc8c463623", LocalDateTime.of(2024, 12, 22, 11, 4), "arianne.watsica", "Steve", "Weber"));

        ruleEntities.add(new RuleEntity(1L,"TOPUP:DEBIT:100%TOPUPGTSPEND:DEBIT", "START_CREDIT", "Кредит для начинающих"));
        ruleEntities.add(new RuleEntity(2L, "USEROF:CREDIT", "ONE_MORE_CREDIT", "Кредит для продолжащих."));

        recommendations.add(new Recommendation(ruleEntities.get(0).getName(), ruleEntities.get(0).getDescription()));
        recommendations.add(new Recommendation(ruleEntities.get(1).getName(), ruleEntities.get(1).getDescription()));
    }

    @Test
    public void getRecommendationProduct() {
        RuleEntity testRule = ruleEntities.get(0);
        RuleEntity oneMoreTestRule = ruleEntities.get(1);
        User testUser = users.get(0);
        when(ruleEntitiesRepository.findAll()).thenReturn(ruleEntities);
        when(ruleService.isRuleCompileAllRequireds(testRule, UUID.fromString(testUser.getId()))).thenReturn(true);
        when(ruleService.isRuleCompileAllRequireds(oneMoreTestRule, UUID.fromString(testUser.getId()))).thenReturn(true);
        Assertions.assertEquals(2, recommendationService.getRecommendationProduct(UUID.fromString(testUser.getId())).size());
    }

    @Test
    public void findUserIdByUsername() {
        String testUsername = "adalberto.spencer";
        when(userRepository.findUserByUsername(testUsername)).thenReturn(Optional.of(users.get(0)));
        Assertions.assertEquals(users.get(0), userRepository.findUserByUsername(testUsername).get());
    }

    @Test
    public void getUserIdByUsername() {
        String testUsername = "adalberto.spencer";
        when(userRepository.findUserByUsername(testUsername)).thenReturn(Optional.of(users.get(0)));
        Assertions.assertEquals(testUsername, userRepository.findUserByUsername(testUsername).get().getUsername());
    }

    @Test
    public void getFirstnameAndLastnameByUsername() {
        String testFullname = "Shay Breitenberg";
        String testUsername = "adalberto.spencer";
        when(userRepository.findUserByUsername(testUsername)).thenReturn(Optional.of(users.get(0)));
        Assertions.assertEquals(testFullname, userRepository.findUserByUsername(testUsername).get().getFirstname() + " " + userRepository.findUserByUsername(testUsername).get().getLastname());
    }

}
