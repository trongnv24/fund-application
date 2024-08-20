package aibles.java.spring.boot.fund.repository;

import aibles.java.spring.boot.fund.entity.FundUser;
import aibles.java.spring.boot.fund.entity.id.FundUserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundUserRepository extends JpaRepository<FundUser, FundUserId> {
    List<FundUser> findByFundId(String fundId);


}
