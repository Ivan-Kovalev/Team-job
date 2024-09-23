package pro.sky.TeamJob.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс описывающий транзакицию
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction {

    /** ID транзакции */
    @Id
    private String id;

    /** тип транзакции */
    private String type;

    /** ID пользователя */
    @OneToOne
    private User userId;

    /** ID продукта */
    @OneToOne
    private Product productId;

    /** сумма транзакции */
    private Long amount;


}
