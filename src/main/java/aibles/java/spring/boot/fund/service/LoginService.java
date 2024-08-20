package aibles.java.spring.boot.fund.service;


import aibles.java.spring.boot.fund.dto.request.LoginRequest;
import aibles.java.spring.boot.fund.dto.response.BaseResponse;
import aibles.java.spring.boot.fund.dto.response.LoginResponse;

public interface LoginService {
    BaseResponse<LoginResponse> login(LoginRequest loginRequest);
}
