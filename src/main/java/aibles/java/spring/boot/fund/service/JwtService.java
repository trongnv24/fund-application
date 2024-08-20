package aibles.java.spring.boot.fund.service;

import aibles.java.spring.boot.fund.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateAccessToken(User user);
    String generateRefreshToken(User user);
    String extractUsername(String jwt);
    String resolveToken(HttpServletRequest request);
    boolean validateToken(String token);
}
