package pro.sky.TeamJob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.sky.TeamJob.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Класс репозиторий для работы с базой данных
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Метод проверяющий использование пользователем продукта определенного типа
     * @param userId id пользователя
     * @param productType тип продукта
     * @return true или false
     */
    @Query(value = "SELECT EXISTS(" +
            "       SELECT * FROM users_to_product utp" +
            "       LEFT JOIN products p ON p.id = utp.product_id" +
            "       WHERE utp.user_id = :userId AND p.type = :productType)", nativeQuery = true)
    boolean findUserOf(@Param("userId") String userId, @Param("productType") String productType);

    /**
     * Метод проверяющий отсутствие использования пользователем продукта определенного типа
     * @param userId id пользователя
     * @param productType тип продукта
     * @return true или false
     */
    @Query(value = "SELECT EXISTS(" +
            "       SELECT * FROM users_to_product utp" +
            "       LEFT JOIN products p ON p.id = utp.product_id" +
            "       WHERE utp.user_id = :userId AND p.type <> :productType)", nativeQuery = true)
    boolean findNotUserOf(@Param("userId") String userId, @Param("productType") String productType);

    /**
     * Метод проверки пополнения пользователем определенного типа продукта на указанную сумму
     * @param userId id пользователя
     * @param productType тип продукта
     * @param queryAmount сумма пополнения для проверки
     * @return true или false
     */
    @Query(value = "SELECT EXISTS(" +
            "       SELECT FROM users_to_product utp" +
            "       LEFT JOIN products p ON p.id = utp.product_id" +
            "       LEFT JOIN transactions t ON t.user_id = utp.user_id" +
            "       WHERE t.type = 'DEPOSIT'" +
            "       AND p.type = :productType" +
            "       AND utp.user_id = :userId" +
            "       AND t.amount = :queryAmount)", nativeQuery = true)
    boolean findTopup(@Param("userId") String userId, @Param("productType") String productType, @Param("queryAmount") Long queryAmount);

    /**
     * Метод проверяющий что сумма пополнений по продукту больше суммы трат по продукту определенного типа
     * @param userId id пользователя
     * @param productType тип продукта
     * @return true или false
     */
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
    boolean findUsersThenTopUpGTSpend(@Param("userId") String userId, @Param("productType") String productType);

    @Query(value = "SELECT EXISTS(" +
            "        SELECT 1" +
            "        FROM products p" +
            "        WHERE p.type = :productType AND (" +
            "                  SELECT SUM(CASE WHEN t.type = 'DEPOSIT' THEN t.amount ELSE 0 END)" +
            "                  FROM transactions t" +
            "                  WHERE t.product_id = p.id" +
            "                    AND t.user_id = :userId" +
            "              ) < (" +
            "                  SELECT SUM(CASE WHEN t.type = 'WITHDRAWAL' THEN t.amount ELSE 0 END)" +
            "                  FROM transactions t" +
            "                  WHERE t.product_id = p.id" +
            "                    AND t.user_id = :userId)" +
            "        LIMIT 1)", nativeQuery = true)
    boolean findUsersThenTopUpGTSpendLess(@Param("userId") String userId, @Param("productType") String productType);

    /**
     * Метод возвращающий сумму списаний по продукту определенного типа
     * @param userId id пользователя
     * @param productType тип продукта
     * @return сумма списаний по продукту
     */
    @Query(value = "SELECT COUNT(amount) " +
            "       FROM transactions t" +
            "       LEFT JOIN products p ON p.id = t.product_id" +
            "       WHERE t.type = 'WITHDRAWAL'" +
            "       AND t.user_id = :userId" +
            "       AND p.type = :productType", nativeQuery = true)
    Long findUsersThenSpendSGT(@Param("userId") String userId, @Param("productType") String productType);

    /**
     * Метод возвращающий сумму пополнений по продукту определенного типа
     * @param userId id пользователя
     * @param productType тип продукта
     * @return сумма пополнений по продукту
     */
    @Query(value = "SELECT COUNT(amount) " +
            "       FROM transactions t" +
            "       LEFT JOIN products p ON p.id = t.product_id" +
            "       WHERE t.type = 'DEPOSIT'" +
            "       AND t.user_id = :userId" +
            "       AND p.type = :productType", nativeQuery = true)
    Long findUsersThenTopupSGT(@Param("userId") String userId, @Param("productType") String productType);

    /**
     * Метод проверяющий активное использование продукта определенного типа. Что колличество транзакций пополнения более 10
     * @param userId id пользователя
     * @param productType тип продукта
     * @return сумма пополнений по продукту
     */
    @Query(value = "SELECT EXISTS(" +
            "        SELECT FROM transactions t" +
            "        JOIN products p ON p.id = t.product_id" +
            "        WHERE t.user_id = '2120e217-f8c0-45b9-9e69-6e5aec9b20ed'" +
            "        AND p.type = 'DEBIT'" +
            "        GROUP BY p.id, p.name" +
            "        HAVING COUNT(*) > 10 LIMIT 1)", nativeQuery = true)
    boolean findUsersThenActiveUserOf(@Param("userId") String userId, @Param("productType") String productType);

    @Query(value = "SELECT CASE WHEN COUNT(*) >= 1 THEN true ELSE false END AS has_eligible_user " +
            "FROM (SELECT u.id AS user_id, " +
            "COUNT(t.id) AS topup_count, " +
            "SUM(t.amount) AS total_topup_amount " +
            "FROM users_to_product utp " +
            "JOIN transactions t ON utp.user_id = t.user_id AND utp.product_id = t.product_id " +
            "JOIN userbase u ON t.user_id = u.id " +
            "JOIN products p ON t.product_id = p.id " +
            "WHERE p.type = :productType " +
            "AND t.amount > :queryAmount " +
            "AND u.id = :userId " +
            "GROUP BY u.id " +
            "HAVING COUNT(t.id) >= :debitNumber " +
            "AND SUM(t.amount) >= 50000) AS subquery", nativeQuery = true)
    boolean findUsersThenActiveUserOfAndPayable(@Param("userId") String userId, @Param("productType") String productType, @Param("queryAmount") Long queryAmount, @Param("debitNumber") Long debitNumber);

    /**
     * Метод поиска данных о пользователе по логину пользователя
     * @param username логин пользователя
     * @return объект пользователя со всеми имеющимися данными о нем
     */
    Optional<User> findUserByUsername(String username);

}