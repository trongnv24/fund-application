package aibles.java.spring.boot.fund.controller;

import aibles.java.spring.boot.fund.dto.request.FundRequest;
import aibles.java.spring.boot.fund.dto.response.FundDetailResponse;
import aibles.java.spring.boot.fund.dto.response.FundResponse;
import aibles.java.spring.boot.fund.service.FundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/funds")
@Slf4j
public class FundController {
    private final FundService fundService;

    public FundController(FundService fundService) {
        this.fundService = fundService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FundResponse create(@Validated @RequestBody FundRequest request) {
        log.info(" === Start api create fund === ");
        log.info(" === Request Body : {} === ", request);
            FundResponse response = fundService.create(request);
            log.info(" === Finish api create, fund Id : {} === ", response.getId());
            return response;
        }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FundResponse> update(@PathVariable("id") String id, @RequestBody FundRequest request) {
        log.info(" === Start api update fund === ");
        log.info(" === Request Body : {} === ", id, request);
        FundResponse response = fundService.update(id, request);
        log.info(" === Finish api update, fund Id : {} === ", response.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<FundResponse>> getAllFunds() {
        log.info(" === Start api update fund === ");
        List<FundResponse> funds = fundService.getAllFunds();
        return new ResponseEntity<>(funds, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FundDetailResponse> getFundById(@PathVariable("id") String id) {
        log.info(" === Start api getById fund === ");
        log.info(" === Request Body : {} === ", id);
        FundDetailResponse response = fundService.getFundDetailById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
