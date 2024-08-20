package aibles.java.spring.boot.fund.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private String id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    private String phone;
    private String fullName;
    @Column(nullable = false, unique = true)
    private String email;
    private String address;
    private boolean isActivated;
    private String createdBy;
    private Long createdAt;
    private String lastUpdatedBy;
    private Long lastUpdatedAt;
}
