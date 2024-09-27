package pro.sky.TeamJob.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.TeamJob.exception.UuidIsNotValidException;
import pro.sky.TeamJob.model.RuleEntity;
import pro.sky.TeamJob.service.RuleService;

/**
 * Класс контроллер правил для рекомендаций
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
@Tag(name="Контроллер правил", description="Занимается созданием, получением и удалением правил")
@RestController
@RequestMapping("/rule/")
@RequiredArgsConstructor
public class RuleController {

    /** Сервис с CRUD операциями по объектам правил */
    private final RuleService ruleService;

    private final CacheController cacheController;

    /**
     * Метод добавления правил игнорируя регистр
     * @param rule объект правила с запросом
     * @return статус выполнения метода
     */
    @Operation(
            summary = "Добавить правило",
            description = "Позволяет добавить правило и рекомендованный продукт по нему"
    )
    @PostMapping
    public ResponseEntity<?> addRecommendation(@RequestBody @Parameter(description = "Правило в формате JSON") RuleEntity rule) {
        rule.setRule(rule.getRule().toUpperCase());
        ruleService.createRule(rule);
        cacheController.clearCacheOfRecommendation();
        return ResponseEntity.ok().body("Новый набор правил для рекоммендации был добавлен.");
    }

    /**
     * Метод обработки ошибок при неверно переданном правиле
     * @param e тип ошибки
     * @return статус с сообщением
     */
    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<String> handler(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
