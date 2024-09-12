package pro.sky.TeamJob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.TeamJob.model.RuleEntity;

public interface RuleEntitiesRepository extends JpaRepository<RuleEntity, Long> {
}
