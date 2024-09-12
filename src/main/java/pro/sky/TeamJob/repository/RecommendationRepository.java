package pro.sky.TeamJob.repository;

import org.hibernate.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface RecommendationRepository extends JpaRepository<String, Transaction> {

    @Query(value = "SELECT EXISTS(" +
            "       SELECT * FROM users_to_product utp" +
            "       LEFT JOIN products p ON p.id = utp.product_id" +
            "       WHERE utp.user_id = :userId AND p.type = :productType)", nativeQuery = true)
    boolean isUserOf(@Param("userId") UUID userId, @Param("productType") String productType);

    @Query(value = "SELECT EXISTS(" +
            "       SELECT * FROM users_to_product utp" +
            "       LEFT JOIN products p ON p.id = utp.product_id" +
            "       WHERE utp.user_id = :userId AND p.type <> :productType)", nativeQuery = true)
    boolean isNotUserOf(@Param("userId") UUID userId, @Param("productType") String productType);

    @Query(value = "SELECT EXISTS(" +
            "       SELECT FROM users_to_product utp" +
            "       LEFT JOIN products p ON p.id = utp.product_id" +
            "       LEFT JOIN transactions t ON t.user_id = utp.user_id" +
            "       WHERE t.type = 'DEPOSIT'" +
            "       AND p.type = :productType" +
            "       AND utp.user_id = :userId" +
            "       AND t.amount = :queryAmount)", nativeQuery = true)
    boolean isTopUp(@Param("userId") UUID userId, @Param("productType") String productType, @Param("queryAmount") Long queryAmount);

    @Query(value = "SELECT EXISTS(" +
            "        SELECT 1" +
            "        FROM products p" +
            "        WHERE p.type = :productType AND (" +
            "                  SELECT SUM(CASE WHEN t.type = 'DEPOSIT' THEN t.amount ELSE 0 END)" +
            "                  FROM transactions t" +
            "                  WHERE t.product_id = p.id" +
            "                    AND t.user_id = :userId" +
            "              ) > (" +
            "                  SELECT SUM(CASE WHEN t.type = 'WITHDRAWAL' THEN t.amount ELSE 0 END)" +
            "                  FROM transactions t" +
            "                  WHERE t.product_id = p.id" +
            "                    AND t.user_id = :userId)" +
            "        LIMIT 1)", nativeQuery = true)
    boolean isTopUpGTSpend(@Param("userId") UUID userId, @Param("productType") String productType);

    @Query(value = "SELECT COUNT(amount) " +
            "       FROM transactions t" +
            "       LEFT JOIN products p ON p.id = t.product_id" +
            "       WHERE t.type = 'WITHDRAWAL'" +
            "       AND t.user_id = :userId" +
            "       AND p.type = :productType", nativeQuery = true)
    Long isSpendSGT(@Param("userId") UUID userId, @Param("productType") String productType);

    @Query(value = "SELECT COUNT(amount) " +
            "       FROM transactions t" +
            "       LEFT JOIN products p ON p.id = t.product_id" +
            "       WHERE t.type = 'DEPOSIT'" +
            "       AND t.user_id = :userId" +
            "       AND p.type = :productType", nativeQuery = true)
    Long isTopUpSGT(@Param("userId") UUID userId, @Param("productType") String productType);

    @Query(value = "SELECT EXISTS(" +
            "        SELECT FROM transactions t" +
            "        JOIN products p ON p.id = t.product_id" +
            "        WHERE t.user_id = '2120e217-f8c0-45b9-9e69-6e5aec9b20ed'" +
            "        AND p.type = 'DEBIT'" +
            "        GROUP BY p.id, p.name" +
            "        HAVING COUNT(*) > 10 LIMIT 1)", nativeQuery = true)
    boolean isActiveUserOf(@Param("userId") UUID userId, @Param("productType") String productType);
}
