package pro.sky.TeamJob.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RecommendationDTO {
    private String productType;
    private String recommendationDescription;
}
