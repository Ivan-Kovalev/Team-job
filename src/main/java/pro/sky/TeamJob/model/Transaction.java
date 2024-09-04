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
    @OneToOne
    private User userId;
    @OneToOne
    private Product productId;
    private Long amount;


}
