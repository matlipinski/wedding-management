package pl.mlipinski.wedding.management.web.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"pl.mlipinski.wedding.management.domain.entity"})
public class WeddingManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeddingManagementApplication.class, args);
	}
}
