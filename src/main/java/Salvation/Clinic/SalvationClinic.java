package Salvation.Clinic;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


//@SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@RequiredArgsConstructor
public class SalvationClinic {


	public static void main(String[] args) {

        SpringApplication.run(SalvationClinic.class, args);
	}




}
