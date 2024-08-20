package aibles.java.spring.boot.fund.dto.response;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundResponse  {
    @Id
    private String id;
    private String description;
    private String ownerId;
    private String name;
    private String createdBy;
    private Long createdAt;
    private String lastUpdatedBy;
    private Long lastUpdatedAt;
    private Long total;
}
