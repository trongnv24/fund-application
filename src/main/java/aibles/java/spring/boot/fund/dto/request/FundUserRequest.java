package aibles.java.spring.boot.fund.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundUserRequest {
    private String fundId;
    private String userId;
}
