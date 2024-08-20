package aibles.java.spring.boot.fund.service.impl;

import aibles.java.spring.boot.fund.dto.request.UserRegisterRequest;
import aibles.java.spring.boot.fund.dto.response.UserRegisterResponse;
import aibles.java.spring.boot.fund.entity.User;
import aibles.java.spring.boot.fund.exception.UserAlreadyExistsException;
import aibles.java.spring.boot.fund.repository.UserRegisterRepository;
import aibles.java.spring.boot.fund.service.OtpService;
import aibles.java.spring.boot.fund.service.RedisService;
import aibles.java.spring.boot.fund.service.UserRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class FundRegisterImpl implements UserRegisterService {
    private final UserRegisterRepository userRegisterRepository;
    private final MailServiceImpl emailService;
    private final OtpService otpService;
    private final RedisService redisService;
    private final PasswordEncoder passwordEncoder;

    public FundRegisterImpl(UserRegisterRepository userRegisterRepository, MailServiceImpl emailService, OtpService otpService, RedisService redisService, PasswordEncoder passwordEncoder) {
        this.userRegisterRepository = userRegisterRepository;
        this.emailService = emailService;
        this.otpService = otpService;
        this.redisService = redisService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserRegisterResponse registerNewUse(UserRegisterRequest request) {
        Optional<User> optionalUserByEmail = userRegisterRepository.findByEmail(request.getEmail());
        if (optionalUserByEmail.isPresent()) {
            throw new UserAlreadyExistsException("Email đã tồn tại");
        }

        // Kiểm tra sự tồn tại của userName
        Optional<User> optionalUserByUsername = userRegisterRepository.findByUserName(request.getUserName());
        if (optionalUserByUsername.isPresent()) {
            throw new UserAlreadyExistsException("userName đã tồn tại");
        }

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUserName(request.getUserName());

        // Mã hóa mật khẩu trước khi lưu
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);

        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setFullName(request.getFullName());
        user.setAddress(request.getAddress());
        user.setActivated(false);  // Ban đầu tài khoản chưa được kích hoạt
        user.setCreatedBy("system");
        user.setCreatedAt(System.currentTimeMillis());
        user.setLastUpdatedBy("system");
        user.setLastUpdatedAt(System.currentTimeMillis());

        userRegisterRepository.save(user);

        String otp = otpService.generateOtp();
        redisService.saveOTP(user.getUserName(), otp, 5L);
        emailService.sendEmail(user.getEmail(), "Your OTP Code", "Your OTP code is: " + otp);

        log.info(" === User entity saved === ");

        return new UserRegisterResponse(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                user.getPhone(),
                user.getFullName(),
                user.getAddress(),
                user.isActivated(),
                user.getCreatedBy(),
                user.getCreatedAt(),
                user.getLastUpdatedBy(),
                user.getLastUpdatedAt()
        );
    }
    @Override
    public void activateUser(String username) {
        User user = userRegisterRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setActivated(true);
        userRegisterRepository.save(user);
        log.info("User {} activated successfully", username);
    }

    @Override
    public String verifyAndActivateUser(String userName, String otp) {
        log.info("Start verifying OTP for userName: {}, with otp: {}", userName, otp);
        if (userName == null || userName.isEmpty()) {
            log.warn("userName cannot be empty");
            return "userName cannot be empty";
        }
        // Log trước khi tìm kiếm user trong cơ sở dữ liệu
        log.info("Looking for user with userName: {}", userName);
        Optional<User> optionalUser = findByUserName(userName);
        if (!optionalUser.isPresent()) {
            log.warn("User with userName {} does not exist", userName);
            return "username does not exist";
        }
        log.info("Verifying OTP for userName: {}", userName);
        boolean isVerified = otpService.verifyOtp(userName, otp);

        if (isVerified) {
            // Log trước khi kích hoạt tài khoản người dùng
            log.info("OTP verified successfully for userName: {}. Activating account...", userName);
            activateUser(userName);
            log.info("Account for userName: {} activated successfully", userName);
            return "OTP verified successfully, account activated.";
        } else {
            log.warn("Invalid OTP provided for userName: {}", userName);
            return "Invalid OTP";
        }
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return userRegisterRepository.findByUserName(userName);
    }
}
