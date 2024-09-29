package pro.sky.TeamJob.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pro.sky.TeamJob.dto.Recommendation;
import pro.sky.TeamJob.exception.UsernameNotExistException;
import pro.sky.TeamJob.model.RuleEntity;
import pro.sky.TeamJob.model.User;
import pro.sky.TeamJob.repository.RuleEntitiesRepository;
import pro.sky.TeamJob.repository.UserRepository;
import pro.sky.TeamJob.service.RecommendationService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Сервис описывающий логику формирования рекомендаций для пользователя
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    /** Репозиторий правил с CRUD опреациями*/
    private final RuleEntitiesRepository ruleEntitiesRepository;

    /** Сервис правил */
    private final RuleServiceImpl ruleService;

    /** Репозиторий пользователей с методами проверки соответствия правилам */
    private final UserRepository userRepository;

    /**
     * Метод формирования рекомендаций для пользователя
     * @param userId id пользователя
     * @return список рекомендаций для пользователя
     */
    @Override
    @Cacheable("getRecommendation")
    public List<Recommendation> getRecommendationProduct(UUID userId) {
        List<RuleEntity> ruleEntities = ruleEntitiesRepository.findAll();
        List<Recommendation> recommendations = new ArrayList<>();
        if (ruleEntities.isEmpty()) {
            return recommendations;
        }
        for (RuleEntity rule: ruleEntities) {
            if (ruleService.isRuleCompileAllRequireds(rule, userId)) {
                recommendations.add(new Recommendation(rule.getName(), rule.getDescription()));
            }
        }
        return recommendations;
    }

    /**
     * Метод поиска пользователя по его логину
     * @param username логин пользователя
     * @return объект пользователя со всей информацией
     */
    public User findUserIdByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotExistException("Ошибка! Пользователя с указанным username не существует."));
    }

    /**
     * Метод получения id пользователя по его логину
     * @param username логин пользователя
     * @return id пользователя в строковом представлении
     */
    public String getUserIdByUsername(String username) {
        return findUserIdByUsername(username).getId();
    }

    /**
     * Метод получения имени и фамилии пользователя по его логину
     * @param username логин пользователя
     * @return имя и фамилию пользователя
     */
    public String getFirstnameAndLastnameByUsername(String username) {
        User user = findUserIdByUsername(username);
        return user.getFirstname() + " " + user.getLastname();
    }

}