package aibles.java.spring.boot.fund.dto.response;

import lombok.Data;

@Data
public class OtpResponse {
    private String code;
    private long timestamp;
    private Object data;
    public OtpResponse(String code, long timestamp, Object data) {
        this.code = code;
        this.timestamp = timestamp;
        this.data = data;
    }
}
