package aibles.java.spring.boot.fund.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyOtpRequest {
    @NotBlank(message = "userName cannot be empty")
    private String userName;

    @NotBlank(message = "OTP cannot be empty")
    private String otp;

}
