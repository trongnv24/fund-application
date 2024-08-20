package aibles.java.spring.boot.fund.entity;

import aibles.java.spring.boot.fund.entity.id.FundUserId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "fund_user")
@IdClass(FundUserId.class)
@AllArgsConstructor
@NoArgsConstructor

public class FundUser {
    @Id
    @NotBlank(message = "fundId không được rỗng")
    @Column(name = "fund_id")
    private String fundId;
    @Id
    @NotBlank(message = "userId không được rỗng")
    @Column(name = "user_id")
    private String userId;

    public FundUser(FundUserId fundUserId) {
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FundUserId that = (FundUserId) o;
        return Objects.equals(fundId, that.getFundId()) && Objects.equals(userId, that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(fundId, userId);
    }
}
