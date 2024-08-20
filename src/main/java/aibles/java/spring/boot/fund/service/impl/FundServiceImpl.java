package aibles.java.spring.boot.fund.service.impl;

import aibles.java.spring.boot.fund.dto.request.FundRequest;
import aibles.java.spring.boot.fund.dto.response.FundDetailResponse;
import aibles.java.spring.boot.fund.dto.response.FundResponse;
import aibles.java.spring.boot.fund.entity.Fund;
import aibles.java.spring.boot.fund.entity.FundUser;
import aibles.java.spring.boot.fund.entity.User;
import aibles.java.spring.boot.fund.repository.FundRepository;
import aibles.java.spring.boot.fund.repository.FundUserRepository;
import aibles.java.spring.boot.fund.repository.UserRegisterRepository;
import aibles.java.spring.boot.fund.service.FundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Slf4j
public class FundServiceImpl implements FundService {

    private final FundRepository fundRepository;
    private final UserRegisterRepository userRepository;
    private final FundUserRepository fundUserRepository;

    public FundServiceImpl(FundRepository fundRepository, UserRegisterRepository userRepository, FundUserRepository fundUserRepository) {
        this.fundRepository = fundRepository;
        this.userRepository = userRepository;
        this.fundUserRepository = fundUserRepository;
    }

    @Override
    public FundResponse create(FundRequest fundRequest) {
        log.info(" === Start api create Fund === ");
        log.info(" === Request Body : {} === ", fundRequest);
        Fund fund = convertDtoToEntity(fundRequest);
        fund.setId(UUID.randomUUID().toString());
        fund = fundRepository.save(fund);
        FundResponse response = convertEntityToFundResponse(fund);
        log.info(" === Finish api create, Fund Id : {} === ", fund.getId());
        return response;
    }


    @Override
    public List<FundResponse> getAllFunds() {
        List<Fund> funds = fundRepository.findAll();
        return funds.stream()
                .map(this::convertEntityToFundResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FundDetailResponse getFundDetailById(String id) {
        //1. query fund by fund id
        Fund fund = fundRepository.findById(id)//TODO:  edit name method query
                .orElseThrow(() -> new RuntimeException("Fund not found"));
        //2. Query userid by fund id
        List<FundUser> fundUserList = fundUserRepository.findByFundId(id);
        FundDetailResponse response = new FundDetailResponse();
        response.setId(fund.getId());
        response.setName(fund.getName());
        response.setDescription(fund.getDescription());
        response.setOwnerId(fund.getOwnerId());
        response.setTotal(fund.getTotal());
        response.setUserList(fundUserList.stream()
                .map(FundUser::getUserId)
//                .map(user -> user.getUserId()) //TODO: cos 2 casch
                .collect(Collectors.toList()));

        return response;
    }

    @Override
    public FundResponse update(String id, FundRequest fundRequest) {
        log.info(" === Start api update Fund with ID: {} === ", id);
        log.info(" === Request Body : {} === ", fundRequest);

        Fund fund = fundRepository.findById(id).orElseThrow(() -> new RuntimeException("Fund not found with ID: " + id));

        Optional<User> optionalUser = userRepository.findById(fundRequest.getOwnerId());
        if (!optionalUser.isPresent()) {
            throw new RuntimeException("Owner not found with ID: " + fundRequest.getOwnerId());
        }
        fund.setName(fundRequest.getName());
        fund.setDescription(fundRequest.getDescription());
        fund.setLastUpdatedAt(System.currentTimeMillis());
        fund.setOwnerId(fundRequest.getOwnerId());
        fund.setLastUpdatedBy("updater-id");
        fund = fundRepository.save(fund);
        FundResponse response = convertEntityToFundResponse(fund);
        log.info(" === Finish api update, Fund Id : {} === ", response.getId());
        return response;
    }

    private Fund convertDtoToEntity(FundRequest fundRequest) {
        Fund fund = new Fund();
        fund.setName(fundRequest.getName());
        fund.setDescription(fundRequest.getDescription());
        fund.setOwnerId(fundRequest.getOwnerId());
        fund.setTotal(0L);
        fund.setCreatedBy(fundRequest.getCreatedBy());
        fund.setCreatedAt(System.currentTimeMillis());
        fund.setLastUpdatedBy(fundRequest.getLastUpdatedBy());
        fund.setLastUpdatedAt(System.currentTimeMillis());
        return fund;
    }

    private FundResponse convertEntityToFundResponse(Fund fund) {
        FundResponse response = new FundResponse();
        response.setId(fund.getId());
        response.setName(fund.getName());
        response.setDescription(fund.getDescription());
        response.setOwnerId(fund.getOwnerId());
        response.setTotal(fund.getTotal());
        response.setCreatedBy(fund.getCreatedBy());
        response.setCreatedAt(fund.getCreatedAt());
        response.setLastUpdatedBy(fund.getLastUpdatedBy());
        response.setLastUpdatedAt(fund.getLastUpdatedAt());
        return response;
    }
}


