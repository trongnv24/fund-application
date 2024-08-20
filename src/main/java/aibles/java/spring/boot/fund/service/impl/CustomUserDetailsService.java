package aibles.java.spring.boot.fund.service.impl;

import aibles.java.spring.boot.fund.entity.User;
import aibles.java.spring.boot.fund.repository.UserRegisterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRegisterRepository userRegisterRepository;

    public CustomUserDetailsService(UserRegisterRepository userRegisterRepository) {
        this.userRegisterRepository = userRegisterRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("Attempting to load user by username: {}", userName);
        User user = userRegisterRepository.findByUserName(userName)
                .orElseThrow(() -> {
                    log.info("User not found with username: {}", userName);
                    return new UsernameNotFoundException("User not found with username: " + userName);
                });
        log.info("User found with username: {} and email: {}", user.getUserName(), user.getEmail());

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                new ArrayList<>()
        );
    }
}