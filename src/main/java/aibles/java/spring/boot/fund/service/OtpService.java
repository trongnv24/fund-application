package aibles.java.spring.boot.fund.service;


public interface OtpService {
   String generateOtp();
   boolean verifyOtp(String userName, String otp);
}
