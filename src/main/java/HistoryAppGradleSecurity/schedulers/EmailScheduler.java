package HistoryAppGradleSecurity.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@EnableScheduling
public class EmailScheduler {

@Autowired
    private JavaMailSender mailSender;

    @Value("${email_username}") private String sender;




    @Scheduled(fixedRate = 12 * 60 * 60 * 1000) // 12 часа в милисекунди
    public void sendScheduledEmails() {
        sendEmail(String.valueOf("pako810129@yahoo.co.uk"),
                "Scheduled Email",
                "This is a scheduled email sent every 12 hours.");

    }

    public void sendSubscriptionEmails() {
        sendEmail(String.valueOf("pako810129@ahoo.co.uk"),
                "Registration Confirmation",
                "Thank you for registering in Ancient History Chanel"
                );

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

