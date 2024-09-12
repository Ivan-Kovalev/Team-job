package pro.sky.TeamJob.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rule;
    private String productType;
    private String recommendationDescription;

    public Rule(String rule, String productType, String recommendationDescription) {
        this.rule = rule;
        this.productType = productType;
        this.recommendationDescription = recommendationDescription;
    }
}
