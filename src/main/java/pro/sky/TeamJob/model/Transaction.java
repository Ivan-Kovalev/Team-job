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
@Table(name = "transactions")
public class Transaction {

    @Id
    private String id;
    private String type;
    @Column(name = "user_id")
    @OneToOne
    private User userId;
    @Column(name = "product_id")
    @OneToOne
    private Product productId;
    private Long amount;


}
