package aibles.java.spring.boot.fund.service;

import aibles.java.spring.boot.fund.dto.request.FundUserRequest;

public interface FundUserService {
    void addFundUser(FundUserRequest request);
    void deleteFundUser(String fundId, String userId);
}
