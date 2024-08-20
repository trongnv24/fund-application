package aibles.java.spring.boot.fund.dto.response;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundDetailResponse {
    @Id
    private String id;
    private String name;
    private String description;
    private String ownerId;
    private Long total;
    private List<String> userList;
}
