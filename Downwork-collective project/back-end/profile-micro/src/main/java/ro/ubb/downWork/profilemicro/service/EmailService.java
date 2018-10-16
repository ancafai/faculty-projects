package ro.ubb.downWork.profilemicro.service;

/**
 * Created by langchristian96 on 11/10/2017.
 */
import org.springframework.mail.SimpleMailMessage;


public interface EmailService {
    public void sendEmail(SimpleMailMessage email);
}