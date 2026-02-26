package co.duvan.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LoanMsvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanMsvcApplication.class, args);
    }

}
