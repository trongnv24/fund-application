package aibles.java.spring.boot.fund.repository;

import aibles.java.spring.boot.fund.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRegisterRepository extends JpaRepository<User, String> {
    Optional<User> findByUserName(String userName);
    Optional<User> findByEmail(String email);
}

