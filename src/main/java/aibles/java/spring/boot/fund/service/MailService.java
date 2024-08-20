package aibles.java.spring.boot.fund.service;

public interface MailService {
    void sendEmail(String to, String subject, String body);
}
