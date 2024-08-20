package aibles.java.spring.boot.fund.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notification")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    private String id;

    private String message;
    private Long amount;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

}
