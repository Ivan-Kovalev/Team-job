package pro.sky.TeamJob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.TeamJob.model.Rule;

public interface RuleRepository extends JpaRepository<Rule, Long> {
}
