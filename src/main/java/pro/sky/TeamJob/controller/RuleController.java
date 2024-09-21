package pro.sky.TeamJob.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.TeamJob.exception.UuidIsNotValidException;
import pro.sky.TeamJob.model.RuleEntity;
import pro.sky.TeamJob.service.RuleService;

@RestController
@RequestMapping("/api/v1/rule/")
@RequiredArgsConstructor
public class RuleController {

    private final RuleService ruleService;

    @PostMapping
    public ResponseEntity addRecommendation(@RequestBody RuleEntity rule) {
        rule.setRule(rule.getRule().toUpperCase());
        ruleService.createRule(rule);
        return ResponseEntity.ok().body("Новый набор правил для рекоммендации был добавлен.");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<String> handler(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
