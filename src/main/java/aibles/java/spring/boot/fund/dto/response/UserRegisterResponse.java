package aibles.java.spring.boot.fund.dto.response;

import aibles.java.spring.boot.fund.dto.request.UserRegisterRequest;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UserRegisterResponse extends UserRegisterRequest {
    @Id
    private String id;
    private boolean isActivated;
    private String createdBy;
    private Long createdAt;
    private String lastUpdatedBy;
    private Long lastUpdatedAt;



    public UserRegisterResponse(String id, String userName, String password, String email, String phone, String fullName, String address, boolean activated, String createdBy, Long createdAt, String lastUpdatedBy, Long lastUpdatedAt) {
        super(userName, password, email, phone, fullName, address);
        this.id = id;
        this.isActivated = activated;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedAt = lastUpdatedAt;
    }
}
