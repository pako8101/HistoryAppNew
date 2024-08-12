package HistoryAppGradleSecurity.schedulers;

import HistoryAppGradleSecurity.model.entity.UserEnt;
import HistoryAppGradleSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@EnableScheduling
public class EmailScheduler {

@Autowired
    private JavaMailSender mailSender;
private final UserRepository userRepository;

    @Value("${email_username}") private String sender;

    public EmailScheduler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Scheduled(fixedRate = 12 * 60 * 60 * 1000) // 12 часа в милисекунди
    public void sendScheduledEmails() {
        sendEmail(String.valueOf("pako810129@yahoo.co.uk"),
                "Scheduled Email",
                "This is a scheduled email sent every 12 hours.");

    }
@Scheduled(cron = "0 * * * * *")
    public void sendSubscriptionEmails() {
        List<UserEnt> pendingUsers = userRepository.findUsersPendingEmails();
        for (UserEnt user : pendingUsers) {
            sendEmail(String.valueOf(user.getEmail()),
                    "Registration Confirmation",
                    "Thank you for registering in Ancient History Chanel"
            );

user.setRegistrationEmailSend(true);
            userRepository.save(user);
            System.out.println("Executing task every 1 minute");
        }


    }


    private String sendEmail(String to,String subject,String text) {
     try {
        SimpleMailMessage message =
                new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
       mailSender.send(message);

        return "Email sent successfully";
    }
       catch (
    Exception e) {


        return  "Error while sending mail!!!";
    }
}



}

