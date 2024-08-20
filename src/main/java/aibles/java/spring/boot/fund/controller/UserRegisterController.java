package aibles.java.spring.boot.fund.controller;

import aibles.java.spring.boot.fund.dto.request.UserRegisterRequest;
import aibles.java.spring.boot.fund.dto.request.VerifyOtpRequest;
import aibles.java.spring.boot.fund.dto.response.ErrorResponse;
import aibles.java.spring.boot.fund.dto.response.UserRegisterResponse;
import aibles.java.spring.boot.fund.service.UserRegisterService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserRegisterController {
    private final UserRegisterService userRegisterService;

    public UserRegisterController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public UserRegisterResponse registerNewUse( @Valid @RequestBody UserRegisterRequest request) {
        log.info(" === Start api new register === ");
        log.info(" === Request Body : {}  === ", request);
        UserRegisterResponse response = userRegisterService.registerNewUse(request);
        log.info(" === Finish api new register, Register Id : {}  === ", response.getId());
        return response;
    }


    @PostMapping("/verify-otp")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ErrorResponse> verifyOtp(@Valid @RequestBody VerifyOtpRequest request) {
        String responseMessage = userRegisterService.verifyAndActivateUser(request.getUserName(), request.getOtp());
        if (responseMessage.equals("OTP verified successfully, account activated.")) {
            return ResponseEntity.ok(new ErrorResponse(responseMessage));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(responseMessage));
        }
    }
}
