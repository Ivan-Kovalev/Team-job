package pro.sky.TeamJob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.sky.TeamJob.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT DISTINCT u.*\n" +
            "FROM userbase u\n" +
            "         JOIN transactions t ON u.id = t.user_id\n" +
            "         JOIN products p ON t.product_id = p.id\n" +
            "WHERE p.type = ?1\n" +
            "  AND t.amount > ?2", nativeQuery = true)
    List<User> findTopup(String type, long amount);

    @Query(value = "SELECT DISTINCT u.*\n" +
            "FROM userbase u\n" +
            "         JOIN users_to_product utp ON u.id = utp.user_id\n" +
            "         JOIN products p ON utp.product_id = p.id\n" +
            "WHERE p.type = ?1", nativeQuery = true)
    List<User> findUserOf(String type);

    @Query(value = "SELECT * FROM userbase", nativeQuery = true)
    List<User> findUsersThenSpendSGT();

    @Query(value = "SELECT * FROM userbase", nativeQuery = true)
    List<User> findUsersThenTopupSGT();

    @Query(value = "SELECT DISTINCT u.*\n" +
            "FROM userbase u\n" +
            "LEFT JOIN users_to_product utp ON u.id = utp.user_id\n" +
            "LEFT JOIN products p ON utp.product_id = p.id\n" +
            "WHERE p.type IS NULL OR p.type <> ?1", nativeQuery = true)
    List<User> findNotUserOf(String type);

    @Query(value = "SELECT * FROM userbase", nativeQuery = true)
    List<User> findUsersThenActiveUserOf();

    @Query(value = "SELECT * FROM userbase", nativeQuery = true)
    List<User> findUsersThenTopUpGTSpend();
}