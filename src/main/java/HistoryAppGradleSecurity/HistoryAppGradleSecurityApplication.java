package HistoryAppGradleSecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HistoryAppGradleSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(HistoryAppGradleSecurityApplication.class, args);
	}

}
