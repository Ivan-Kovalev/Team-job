package pro.sky.TeamJob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.sky.TeamJob.model.Product;

import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<String, Product> {

    @Query("SELECT p FROM Product p "
            + "WHERE p.name LIKE 'DEBIT%' "
            + "AND NOT EXISTS (SELECT 1 FROM Product p2 WHERE p2.name LIKE 'INVEST%' AND p2.userId LIKE p.userId) "
            + "AND EXISTS (SELECT 1 FROM Transaction t WHERE t.productId = p.id AND t.amount <= 1000 AND t.name LIKE 'SAVING%')")
    Product recommendedProductInvest(@Param("userId") String userId);

    @Query("SELECT p FROM Product p "
            + "WHERE p.type = 'DEBIT' "
            + "AND EXISTS (SELECT 1 FROM Transaction t1 WHERE t1.productId = p.id AND t1.amount > 10000 AND (t1.type = 'DEPOSIT' OR t1.type = 'SAVING_DEPOSIT')) "
            + "GROUP BY p "
            + "HAVING COUNT(DISTINCT t.id) >= 5 "
            + "AND SUM(CASE WHEN t.type = 'DEPOSIT' OR t.type = 'SAVING_DEPOSIT' THEN t.amount ELSE -t.amount END) > 0 "
            + "ORDER BY p.id "
            + "LIMIT 1")
    Product recommendedProductSaving(@Param("userId") String userId);

    @Query("SELECT p FROM Product p "
            + "WHERE p.type <> 'CREDIT' "
            + "AND EXISTS (SELECT 1 FROM Transaction t1 WHERE t1.productId = p.id AND t1.type = 'DEPOSIT') "
            + "AND (SELECT SUM(CASE WHEN t.type = 'DEPOSIT' THEN t.amount ELSE -t.amount END) FROM Transaction t WHERE t.productId = p.id) > 0 "
            + "AND (SELECT SUM(CASE WHEN t.type = 'WITHDRAWAL' AND t.createdAt >= CURRENT_DATE - 90 THEN t.amount ELSE 0 END) FROM Transaction t WHERE t.productId = p.id) > 100000 "
            + "ORDER BY p.id "
            + "LIMIT 1")
    Product recommendedProductCredit(@Param("userId") String userId);

}
