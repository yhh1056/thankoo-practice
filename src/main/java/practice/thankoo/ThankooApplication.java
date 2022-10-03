package practice.thankoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ThankooApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThankooApplication.class, args);
    }
}
