package pro.sky.TeamJob.repository;

import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.sky.TeamJob.model.Product;
import pro.sky.TeamJob.model.User;

import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT DISTINCT u.* FROM userbase u\n" +
            "LEFT JOIN users_to_product utp_debit ON u.id = utp_debit.user_id\n" +
            "LEFT JOIN products p_debit ON utp_debit.product_id = p_debit.id AND p_debit.type LIKE 'DEBIT%'\n" +
            "LEFT JOIN users_to_product utp_invest ON u.id = utp_invest.user_id\n" +
            "LEFT JOIN products p_invest ON utp_invest.product_id = p_invest.id AND p_invest.type LIKE 'INVEST%'\n" +
            "LEFT JOIN users_to_product utp_saving ON u.id = utp_saving.user_id\n" +
            "LEFT JOIN products p_saving ON utp_saving.product_id = p_saving.id AND p_saving.type LIKE 'SAVING%'\n" +
            "LEFT JOIN transactions t ON t.user_id = u.id AND t.product_id = p_saving.id AND t.amount <= 1000\n" +
            "WHERE p_invest.id IS NULL\n" +
            "GROUP BY u.id, u.\"registration_date\", u.username, u.\"firstname\", u.\"lastname\"\n" +
            "HAVING COUNT(DISTINCT p_debit.id) > 0\n" +
            "AND COUNT(DISTINCT t.id) > 0;", nativeQuery = true)
    List<User> recommendedProductInvest();

    @Query(value = "select * FROM products", nativeQuery = true)
    List<User> recommendedProductSaving(String userId);

    @Query(value = "select * FROM products", nativeQuery = true)
    List<User> recommendedProductCredit(String userId);

}