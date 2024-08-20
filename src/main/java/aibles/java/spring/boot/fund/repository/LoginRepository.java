package aibles.java.spring.boot.fund.repository;

import aibles.java.spring.boot.fund.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LoginRepository extends JpaRepository<User, String> {
    @Query("SELECT u FROM User u WHERE u.userName = :userName")
    Optional<User> findByUsername(@Param("userName") String userName);

}
