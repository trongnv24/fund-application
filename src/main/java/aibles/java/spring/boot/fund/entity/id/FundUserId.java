    package aibles.java.spring.boot.fund.entity.id;

    import jakarta.persistence.Embeddable;

    import java.io.Serializable;
    import java.util.Objects;

    @Embeddable
    public class FundUserId implements Serializable {
        private String fundId;
        private String userId;

        public FundUserId() {}

        public FundUserId(String fundId, String userId) {
            this.fundId = fundId;
            this.userId = userId;
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
            return Objects.equals(fundId, that.fundId) && Objects.equals(userId, that.userId);
        }
        @Override
        public int hashCode() {
            return Objects.hash(fundId, userId);
        }

    }
