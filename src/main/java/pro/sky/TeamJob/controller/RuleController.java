package pro.sky.TeamJob.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.TeamJob.model.Rule;
import pro.sky.TeamJob.service.RuleService;

@RestController
@RequiredArgsConstructor
public class RuleController {

    /*
     *Стандартный контроллер с CRUD операциями без Update
     */

    private RuleService ruleService;

    @PostMapping
    public Rule addRule(Rule rule) {
        return ruleService.add(rule);
    }

    @GetMapping
    public Rule getRule(Long ruleId) {
        return ruleService.getRule(ruleId);
    }

    @DeleteMapping
    public void delete(Long ruleId) {
        ruleService.delete(ruleId);
    }
}
