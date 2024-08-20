package aibles.java.spring.boot.fund.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "fund")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fund {
        @Id
        @Column(name = "id")
        private String id;

        @Column(name = "name", nullable = false)
        private String name;

        @Column(name = "description")
        private String description;

        @Column(name = "owner_id", nullable = false)
        private String ownerId;

        @Column(name = "total")
        private Long total;

        @Column(name = "created_by")
        private String createdBy;

        @Column(name = "created_at")
        private Long createdAt;

        @Column(name = "last_update_by")
        private String lastUpdatedBy;

        @Column(name = "last_update_at")
        private Long lastUpdatedAt;

        @PrePersist
        protected void onCreate() {
                if (createdAt == null) {
                        createdAt = Instant.now().getEpochSecond();
                }
                if (createdBy == null) {
                        createdBy = "creator-id-value";
                }
//                this.ownerId = UUID.randomUUID().toString();
        }

}
