package aibles.java.spring.boot.fund.repository;

import aibles.java.spring.boot.fund.entity.Fund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FundRepository extends JpaRepository<Fund, String> {
    Optional<Fund> findById(@Param("id") String id);
}
