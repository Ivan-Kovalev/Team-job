package pro.sky.TeamJob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.TeamJob.model.Rule;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {
}
