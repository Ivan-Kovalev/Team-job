package pro.sky.TeamJob.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.TeamJob.model.Rule;
import pro.sky.TeamJob.repository.RuleRepository;
import pro.sky.TeamJob.service.RuleService;

@Service
@RequiredArgsConstructor
public class RuleServiceImpl implements RuleService {

    private RuleRepository ruleRepository;

    @Override
    public Rule add(Rule rule) {
        return ruleRepository.save(rule);
    }

    @Override
    public Rule getRule(Long ruleId) {
        return ruleRepository.findById(ruleId).orElse(null);
    }

    @Override
    public void delete(Long ruleId) {
        ruleRepository.deleteById(ruleId);
    }
}
