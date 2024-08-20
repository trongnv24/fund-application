package aibles.java.spring.boot.fund.service.impl;

import aibles.java.spring.boot.fund.service.OtpService;
import aibles.java.spring.boot.fund.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
@Slf4j
public class OtpServiceImpl implements OtpService {
    private final RedisService redisService;
    private static final int OTP_LENGTH = 6;

    public OtpServiceImpl(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public String generateOtp() {
        Random random = new Random();
        StringBuilder otp = new StringBuilder(OTP_LENGTH);
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }

    @Override
    public boolean verifyOtp(String userName, String otp) {
        String storedOtp = redisService.findOtp(userName);
        log.info("Stored OTP for user {}: {}", userName, storedOtp);
        log.info("Received OTP: {}", otp);
        if (storedOtp != null && storedOtp.equals(otp)) {
            redisService.clearActiveOtp(userName);
            log.info("OTP verified successfully for user {}", userName);
            return true;
        }
        log.warn("OTP verification failed for user {}", userName);
        return false;
    }


}