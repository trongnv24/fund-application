package aibles.java.spring.boot.fund.service.impl;

import aibles.java.spring.boot.fund.dto.request.FundUserRequest;
import aibles.java.spring.boot.fund.entity.Fund;
import aibles.java.spring.boot.fund.entity.FundUser;
import aibles.java.spring.boot.fund.entity.User;
import aibles.java.spring.boot.fund.entity.id.FundUserId;
import aibles.java.spring.boot.fund.exception.ResourceNotFoundException;
import aibles.java.spring.boot.fund.repository.FundRepository;
import aibles.java.spring.boot.fund.repository.FundUserRepository;
import aibles.java.spring.boot.fund.repository.UserRegisterRepository;
import aibles.java.spring.boot.fund.service.FundUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
public class FundUserServiceImpl implements FundUserService {
    private final FundUserRepository fundUserRepository;
    private final FundRepository fundRepository;
    private final UserRegisterRepository userRepository;

    public FundUserServiceImpl(FundUserRepository fundUserRepository, FundRepository fundRepository, UserRegisterRepository userRepository1) {
        this.fundUserRepository = fundUserRepository;
        this.fundRepository = fundRepository;

        this.userRepository = userRepository1;
    }

    @Override
    public void addFundUser(FundUserRequest request) {
        if (request.getFundId() == null || request.getFundId().isEmpty()) {
            throw new IllegalArgumentException("fundId không được rỗng");
        }
        if (request.getUserId() == null || request.getUserId().isEmpty()) {
            throw new IllegalArgumentException("userId không được rỗng");
        }

        Optional<Fund> fund = fundRepository.findById(request.getFundId());
        if (!fund.isPresent()) {
            throw new ResourceNotFoundException("Fund không tồn tại");
        }

        Optional<User> user = userRepository.findById(request.getUserId());
        if (!user.isPresent()) {
            throw new ResourceNotFoundException("User không tồn tại");
        }

        FundUser fundUser = new FundUser(request.getFundId(), request.getUserId());
        fundUserRepository.save(fundUser);

        log.info(" === Fund user added successfully === ");
    }

    @Override
    public void deleteFundUser(String fundId, String userId) {
        if (fundId == null || fundId.isEmpty()) {
            throw new IllegalArgumentException("fundId không được rỗng");
        }
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("userId không được rỗng");
        }

        FundUserId fundUserId = new FundUserId(fundId, userId);
        if (!fundUserRepository.existsById(fundUserId)) {
            throw new ResourceNotFoundException("FundUser không tồn tại");
        }

        fundUserRepository.deleteById(fundUserId);
        log.info(" === Fund user deleted successfully === ");
    }
    }

