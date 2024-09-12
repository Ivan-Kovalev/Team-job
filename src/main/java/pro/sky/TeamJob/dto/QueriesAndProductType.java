package pro.sky.TeamJob.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QueriesAndProductType {
    private String queries;
    private String productType;
    private Long argument;
}
