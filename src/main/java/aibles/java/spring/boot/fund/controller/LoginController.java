package aibles.java.spring.boot.fund.controller;

import aibles.java.spring.boot.fund.dto.request.LoginRequest;
import aibles.java.spring.boot.fund.dto.response.BaseResponse;
import aibles.java.spring.boot.fund.dto.response.LoginResponse;
import aibles.java.spring.boot.fund.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/users")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<BaseResponse<LoginResponse>> login(@Validated @RequestBody LoginRequest request) {
        log.info(" === Start api new login === ");
        log.info(" === Request Body : {} === ", request);
        BaseResponse<LoginResponse> loginResponse = loginService.login(request);
        log.info(" === Finish api new login === ");
        return ResponseEntity.ok(loginResponse);
    }
}
