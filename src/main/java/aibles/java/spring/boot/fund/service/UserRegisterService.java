package aibles.java.spring.boot.fund.service;

import aibles.java.spring.boot.fund.dto.request.UserRegisterRequest;
import aibles.java.spring.boot.fund.dto.response.UserRegisterResponse;
import aibles.java.spring.boot.fund.entity.User;

import java.util.Optional;
public interface UserRegisterService {
    UserRegisterResponse registerNewUse(UserRegisterRequest request);
    void activateUser(String username);
    String verifyAndActivateUser(String userName, String otp);
    Optional<User> findByUserName(String userName);
}
