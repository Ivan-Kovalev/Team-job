package pro.sky.TeamJob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.TeamJob.model.RuleEntity;

/**
 * Класс репозиторий по работе с базой данных для правил
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
public interface RuleEntitiesRepository extends JpaRepository<RuleEntity, Long> {
}
