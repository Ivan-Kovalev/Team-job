package pro.sky.TeamJob.utils;

import lombok.experimental.UtilityClass;
import pro.sky.TeamJob.dto.Queries;
import pro.sky.TeamJob.dto.QueryEntity;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class RuleStringParserUtils {

    public boolean isRequestValid(String request) {
        List<QueryEntity> queryEntities = convertRuleStringToQueryEntitesList(request);
        return false;
    }

    public List<QueryEntity> convertRuleStringToQueryEntitesList(String ruleString) throws IllegalArgumentException {
        String[] rules = ruleString.split("%");
        List<QueryEntity> queryEntities = new ArrayList<>();
        for(String rule : rules) {
            String[] nameAndArguments = rule.split(":");
            Queries ruleType = Queries.valueOf(nameAndArguments[0]);
            String[] arguments = new String[]{nameAndArguments[1]};
            queryEntities.add(new QueryEntity(ruleType, arguments));
        }
        return queryEntities;
    }


}
