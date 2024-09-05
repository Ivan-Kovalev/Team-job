package pro.sky.TeamJob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.sky.TeamJob.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

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
    List<User> findUsersThatAppropriateToProductInvest();

    @Query(value = "SELECT u.id, u.username, u.firstName, u.lastName, u.registration_date\n" +
            "FROM userbase u\n" +
            "         JOIN users_to_product utp ON u.id = utp.user_id\n" +
            "         JOIN products p ON utp.product_id = p.id\n" +
            "         JOIN transactions t ON u.id = t.user_id AND p.id = t.product_id\n" +
            "WHERE p.type = 'DEBIT'\n" +
            "GROUP BY u.id\n" +
            "HAVING\n" +
            "    COUNT(CASE WHEN p.type IN ('DEBIT', 'SAVING') AND t.amount > 10000 THEN 1 END) >= 5\n" +
            "   AND (\n" +
            "    SUM(CASE WHEN p.type = 'DEBIT' AND t.type = 'ADD' THEN t.amount ELSE 0 END) >=\n" +
            "    SUM(CASE WHEN p.type = 'DEBIT' AND t.type = 'SUBTRACT' THEN t.amount ELSE 0 END)\n" +
            "        OR\n" +
            "    SUM(CASE WHEN p.type = 'DEBIT' AND t.type = 'ADD' THEN t.amount ELSE 0 END) = 0\n" +
            "    )\n", nativeQuery = true)
    List<User> findUsersThatAppropriateToProductSaving();

    @Query(value = "SELECT * FROM userbase u\n" +
            "WHERE EXISTS (\n" +
            "    SELECT 1\n" +
            "    FROM (\n" +
            "             SELECT user_id\n" +
            "             FROM transactions\n" +
            "             WHERE type = 'WITHDRAWAL'\n" +
            "               AND product_id IN (\n" +
            "                 SELECT id FROM products WHERE type = 'DEBIT'\n" +
            "             )\n" +
            "             GROUP BY user_id\n" +
            "             HAVING SUM(amount) > 100000\n" +
            "         ) AS t\n" +
            "    WHERE t.user_id = u.id\n" +
            ")\n" +
            "  AND EXISTS (\n" +
            "    SELECT 1\n" +
            "    FROM transactions t2\n" +
            "    WHERE t2.user_id = u.id\n" +
            "      AND t2.type = 'WITHDRAWAL'\n" +
            "      AND t2.id = (\n" +
            "        SELECT MAX(id)\n" +
            "        FROM transactions t3\n" +
            "        WHERE t3.user_id = u.id\n" +
            "          AND t3.type = 'WITHDRAWAL'\n" +
            "          AND t3.product_id IN (\n" +
            "            SELECT id FROM products WHERE type = 'DEBIT'\n" +
            "        )\n" +
            "    )\n" +
            ");", nativeQuery = true)
    List<User> findUsersThatAppropriateToProductSimpleCredit();

}