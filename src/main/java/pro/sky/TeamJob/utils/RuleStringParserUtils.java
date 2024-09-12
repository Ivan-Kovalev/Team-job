package pro.sky.TeamJob.utils;

import lombok.experimental.UtilityClass;
import pro.sky.TeamJob.dto.Queries;
import pro.sky.TeamJob.dto.QueryEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public class RuleStringParserUtils {

    public boolean isRequestValid(String request) {
        if (!request.contains("%") || !request.contains(":")) {
            return false;
        }
        List<QueryEntity> queryEntities = convertRuleStringToQueryEntitesList(request);
        return true;
    }

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

    public boolean validateArguments(Queries ruleName, String[] arguments) {
        switch (ruleName) {
            case UserOf -> userOfArgumentsValidate(arguments);
            case NotUserOf -> notUserOfArgumentsValidate(arguments);
            case Topup -> totupArgumentsValidate(arguments);
            case TopUpGTSpend -> topUpGTSpendValidate(arguments);
            case SpendSGT -> spendSGTArgumentsValidate(arguments);
            case TopupSGT -> topupSGTArgumentsValidate(arguments);
            case ActiveUserOf -> activeUserOfArgumentsValidate(arguments);
        }
        return true;
    }

    public void userOfArgumentsValidate(String[] arguments) throws IllegalArgumentException {
        if (Arrays.stream(arguments).count() == 1) {
        } else {
            throw new IllegalArgumentException("Ошибка! Число передаваемых аргумента для правила UserOf должно равняться 1");
        }
    }

    public void notUserOfArgumentsValidate(String[] arguments) throws IllegalArgumentException {
        if (Arrays.stream(arguments).count() == 1) {
        } else {
            throw new IllegalArgumentException("Ошибка! Число передаваемых аргумента для правила NotUserOf должно равняться 1");
        }
    }

    public void totupArgumentsValidate(String[] arguments) throws IllegalArgumentException, NumberFormatException {
        if (Arrays.stream(arguments).count() == 2) {
            try {
                Long.parseLong(arguments[1]);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Ошибка! Второй переданный аргумент для правила Totup должен быть числом");
            }
        } else {
            throw new IllegalArgumentException("Ошибка! Число передаваемых аргумента для правила NotUserOf должно равняться 1");
        }
    }

    public void topUpGTSpendValidate(String[] arguments) throws IllegalArgumentException {

    }

    public void spendSGTArgumentsValidate(String[] arguments) throws IllegalArgumentException {

    }

    public void topupSGTArgumentsValidate(String[] arguments) throws IllegalArgumentException {

    }

    public void activeUserOfArgumentsValidate(String[] arguments) throws IllegalArgumentException {

    }

}
