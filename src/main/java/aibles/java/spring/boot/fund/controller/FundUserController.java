package aibles.java.spring.boot.fund.controller;

import aibles.java.spring.boot.fund.dto.request.FundUserRequest;
import aibles.java.spring.boot.fund.service.FundUserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fund_user")
@Slf4j
public class FundUserController {
    private final FundUserService fundUserService;

    public FundUserController(FundUserService fundUserService) {
        this.fundUserService = fundUserService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addFundUser(@Valid @RequestBody FundUserRequest request) {
        fundUserService.addFundUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Fund user added successfully");
    }
    @DeleteMapping
    public ResponseEntity<String> deleteFundUser(@RequestParam String fundId, @RequestParam String userId) {
        fundUserService.deleteFundUser(fundId, userId);
        return ResponseEntity.status(HttpStatus.OK).body("Fund user deleted successfully");
    }
}
