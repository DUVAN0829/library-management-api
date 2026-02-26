package co.duvan.copy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CopyMsvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(CopyMsvcApplication.class, args);
	}

}
