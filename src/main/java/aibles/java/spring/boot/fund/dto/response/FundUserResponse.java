package aibles.java.spring.boot.fund.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundUserResponse {
    private String fundId;
    private String userId;
}
