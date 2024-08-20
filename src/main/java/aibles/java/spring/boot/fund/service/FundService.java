package aibles.java.spring.boot.fund.service;

import aibles.java.spring.boot.fund.dto.request.FundRequest;
import aibles.java.spring.boot.fund.dto.response.FundDetailResponse;
import aibles.java.spring.boot.fund.dto.response.FundResponse;
import java.util.List;

public interface FundService {
    FundResponse create(FundRequest fundRequest);
    List<FundResponse> getAllFunds();
    FundDetailResponse getFundDetailById(String id);
    FundResponse update(String id, FundRequest fundRequest);

}
