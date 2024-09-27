package pro.sky.TeamJob.utils;

import lombok.experimental.UtilityClass;
import pro.sky.TeamJob.dto.Queries;
import pro.sky.TeamJob.dto.QueryEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Утилитарный класс с методами валидации и парсинга запросов из правил
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
@UtilityClass
public class RuleStringParserUtils {

    /**
     * Метод проверки запроса на соответствие заданной формы "text:text%text:text"
     * @param request строка с запросом
     * @return true или false
     */
    public boolean isRequestValid(String request) {
        if (!request.contains("%") && !request.contains(":")) {
            return false;
        }
        convertRuleStringToQueryEntitesList(request);
        return true;
    }

    /**
     * Метод создания списка правил из запроса для проверки пользователя
     * @param ruleString запрос с правилами
     * @return коллекция правил
     * @throws IllegalArgumentException запрос не корректный
     */
    public List<QueryEntity> convertRuleStringToQueryEntitesList(String ruleString) throws IllegalArgumentException {
        String[] rules = ruleString.split("%");
        List<QueryEntity> queryEntities = new ArrayList<>();
        for(String rule : rules) {
            String[] nameAndArguments = rule.split(":");
            Queries ruleType = Queries.valueOf(nameAndArguments[0]);
            String[] arguments = Arrays.copyOfRange(nameAndArguments, 1, nameAndArguments.length);
            validateArguments(ruleType, arguments);
            queryEntities.add(new QueryEntity(ruleType, arguments));
        }
        return queryEntities;
    }

    /**
     * Метод проверки правил на наличие таковых в заданных константах объекта Queries
     * @param ruleName название правила
     * @param arguments аргументы правила
     * @return true или false
     */
    public boolean validateArguments(Queries ruleName, String[] arguments) {
        switch (ruleName) {
            case USEROF -> userOfArgumentsValidate(arguments);
            case NOTUSEROF -> notUserOfArgumentsValidate(arguments);
            case TOPUP -> totupArgumentsValidate(arguments);
            case TOPUPGTSPEND -> topUpGTSpendValidate(arguments);
            case TOPUPGTSPENDLESS -> topUpGTSpendLessValidate(arguments);
            case SPENDSGT -> spendSGTArgumentsValidate(arguments);
            case TOPUPSGT -> topupSGTArgumentsValidate(arguments);
            case ACTIVEUSEROF -> activeUserOfArgumentsValidate(arguments);
            case ACTIVEUSERANDPAYABLE -> activeUserAndPayableOfArgumentsValidate(arguments);
        }
        return true;
    }

    /**
     * Метод проверки колличества аргументов для правила
     * @param arguments аргументы
     * @throws IllegalArgumentException число передаваемых аргументов не соответствует правилу
     */
    public void userOfArgumentsValidate(String[] arguments) throws IllegalArgumentException {
        if (Arrays.stream(arguments).count() == 1) {
        } else {
            throw new IllegalArgumentException("Ошибка! Число передаваемых аргумента для правила USEROF должно равняться 1");
        }
    }

    /**
     * Метод проверки колличества аргументов для правила
     * @param arguments аргументы
     * @throws IllegalArgumentException число передаваемых аргументов не соответствует правилу
     */
    public void notUserOfArgumentsValidate(String[] arguments) throws IllegalArgumentException {
        if (Arrays.stream(arguments).count() == 1) {
        } else {
            throw new IllegalArgumentException("Ошибка! Число передаваемых аргумента для правила NOTUSEROF должно равняться 1");
        }
    }

    /**
     * Метод проверки колличества аргументов для правила
     * @param arguments аргументы
     * @throws IllegalArgumentException число передаваемых аргументов не соответствует правилу
     */
    public void totupArgumentsValidate(String[] arguments) throws IllegalArgumentException, NumberFormatException {
        if (Arrays.stream(arguments).count() == 2) {
            try {
                Long.parseLong(arguments[1]);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Ошибка! Второй переданный аргумент для правила TOTUP должен быть числом");
            }
        } else {
            throw new IllegalArgumentException("Ошибка! Число передаваемых аргумента для правила TOTUP должно равняться 1");
        }
    }

    /**
     * Метод проверки колличества аргументов для правила
     * @param arguments аргументы
     * @throws IllegalArgumentException число передаваемых аргументов не соответствует правилу
     */
    public void topUpGTSpendValidate(String[] arguments) throws IllegalArgumentException {
        if (Arrays.stream(arguments).count() == 1) {
        } else {
            throw new IllegalArgumentException("Ошибка! Число передаваемых аргумента для правила TOPUPGTSPEND должно равняться 1");
        }
    }

    /**
     * Метод проверки колличества аргументов для правила
     * @param arguments аргументы
     * @throws IllegalArgumentException число передаваемых аргументов не соответствует правилу
     */
    public void topUpGTSpendLessValidate(String[] arguments) throws IllegalArgumentException {
        if (Arrays.stream(arguments).count() == 1) {
        } else {
            throw new IllegalArgumentException("Ошибка! Число передаваемых аргумента для правила TOPUPGTSPENDLESS должно равняться 1");
        }
    }

    /**
     * Метод проверки колличества аргументов для правила
     * @param arguments аргументы
     * @throws IllegalArgumentException число передаваемых аргументов не соответствует правилу
     */
    public void spendSGTArgumentsValidate(String[] arguments) throws IllegalArgumentException {
        if (Arrays.stream(arguments).count() == 1) {
            try {
                Long.parseLong(arguments[0]);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Ошибка! Переданный аргумент для правила SPENDSGT должен быть числом");
            }
        } else {
            throw new IllegalArgumentException("Ошибка! Число передаваемых аргумента для правила SPENDSGT должно равняться 1");
        }
    }

    /**
     * Метод проверки колличества аргументов для правила
     * @param arguments аргументы
     * @throws IllegalArgumentException число передаваемых аргументов не соответствует правилу
     */
    public void topupSGTArgumentsValidate(String[] arguments) throws IllegalArgumentException {
        if (Arrays.stream(arguments).count() == 1) {
            try {
                Long.parseLong(arguments[0]);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Ошибка! Переданный аргумент для правила TOPUPSGT должен быть числом");
            }
        } else {
            throw new IllegalArgumentException("Ошибка! Число передаваемых аргумента для правила TOPUPSGT должно равняться 1");
        }
    }

    /**
     * Метод проверки колличества аргументов для правила
     * @param arguments аргументы
     * @throws IllegalArgumentException число передаваемых аргументов не соответствует правилу
     */
    public void activeUserOfArgumentsValidate(String[] arguments) throws IllegalArgumentException {
        if (Arrays.stream(arguments).count() == 1) {
        } else {
            throw new IllegalArgumentException("Ошибка! Число передаваемых аргумента для правила ACTIVEUSEROF должно равняться 1");
        }
    }

    /**
     * Метод проверки колличества аргументов для правила
     * @param arguments аргументы
     * @throws IllegalArgumentException число передаваемых аргументов не соответствует правилу
     */
    public void activeUserAndPayableOfArgumentsValidate(String[] arguments) throws IllegalArgumentException {
        if (Arrays.stream(arguments).count() == 3) {
            try {
                Long.parseLong(arguments[1]);
                Long.parseLong(arguments[2]);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Ошибка! Второй и третий переданный аргумент для правила TOTUP должен быть числом");
            }
        } else {
            throw new IllegalArgumentException("Ошибка! Число передаваемых аргумента для правила TOTUP должно равняться 3");
        }
    }

}
