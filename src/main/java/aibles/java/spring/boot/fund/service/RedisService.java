package aibles.java.spring.boot.fund.service;

public interface RedisService {
    void saveOTP(String userName, String otp,  Long duration);
    String findOtp(String userName);
    void clearActiveOtp(String userName);
    void save(String accessTokenKey, String accessToken, Long expirationMs);
}
