package pro.sky.TeamJob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.sky.TeamJob.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

//    @Query
//    List<User> findUsersThenTotUp();
//
//    @Query
//    List<User> findUsersThenUserOf();
//
//    @Query
//    List<User> findUsersThenSpendSGT();
//
//    @Query
//    List<User> findUsersThenTopupSGT();
//
//    @Query
//    List<User> findUsersThenNotUserOf();
//
//    @Query
//    List<User> findUsersThenActiveUserOf();
//
//    @Query
//    List<User> findUsersThenTopUpGTSpend();
}