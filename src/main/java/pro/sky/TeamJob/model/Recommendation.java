package pro.sky.TeamJob.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recommendation")
public class Recommendation {

    @Id
    private String id;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private String descriptionProduct;

    public Recommendation(Product product, String descriptionProduct) {
        this.product = product;
        this.descriptionProduct = descriptionProduct;
    }

}
