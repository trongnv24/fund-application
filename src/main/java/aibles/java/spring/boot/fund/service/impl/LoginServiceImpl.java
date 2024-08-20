package aibles.java.spring.boot.fund.service.impl;

import aibles.java.spring.boot.fund.constant.CacheConstant;
import aibles.java.spring.boot.fund.dto.request.LoginRequest;
import aibles.java.spring.boot.fund.dto.response.BaseResponse;
import aibles.java.spring.boot.fund.dto.response.LoginResponse;
import aibles.java.spring.boot.fund.entity.User;
import aibles.java.spring.boot.fund.exception.ResourceNotFoundException;
import aibles.java.spring.boot.fund.repository.LoginRepository;
import aibles.java.spring.boot.fund.service.JwtService;
import aibles.java.spring.boot.fund.service.LoginService;
import aibles.java.spring.boot.fund.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RedisService redisService;
    private final JwtProperties jwtProperties;

    public LoginServiceImpl(LoginRepository loginRepository, PasswordEncoder passwordEncoder, JwtService jwtService, RedisService redisService, JwtProperties jwtProperties) {
        this.loginRepository = loginRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.redisService = redisService;
        this.jwtProperties = jwtProperties;
    }

    @Override
    public BaseResponse<LoginResponse> login(LoginRequest loginRequest) {
        log.info("Login request: {}", loginRequest);

        User user = loginRepository.findByUsername(loginRequest.getUserName())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        log.info(" === User logged in: {}", user);
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            log.info("Password does not match for username: {}", loginRequest.getUserName());
            throw new RuntimeException("Invalid password");
        }

        if (!user.isActivated()) {
            log.info("Users  needs to be activated: {}", loginRequest.getUserName());
            throw new RuntimeException("Users needs to be activated");
        }

        // Generate JWT tokens
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        // Prepare Redis keys
        String accessTokenKey = user.getUserName()+ "_" + CacheConstant.ACCESS_TOKEN_KEY;
        String refreshTokenKey = user.getUserName() + "_" + CacheConstant.REFRESH_TOKEN_KEY;

        // Save tokens in Redis
        redisService.save(accessTokenKey, accessToken, jwtProperties.getExpirationMs());
        redisService.save(refreshTokenKey, refreshToken, jwtProperties.getRefreshExpirationMs());


        log.info("Login successful for username: {}", loginRequest.getUserName());

        // Prepare response data
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAccessToken(accessToken);
        loginResponse.setRefreshToken(refreshToken);
        loginResponse.setAccessTokenExpiration(jwtProperties.getExpirationMs());
        loginResponse.setRefreshTokenExpiration(jwtProperties.getRefreshExpirationMs());

        BaseResponse<LoginResponse> response = new BaseResponse<>();
        response.setCode("success");
        response.setTimestamp(System.currentTimeMillis());
        response.setData(loginResponse);
        return response;
    }
}
