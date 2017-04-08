package pl.mlipinski.wedding.management.web.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"pl.mlipinski.wedding.management.domain.entity"})
@EnableJpaRepositories(basePackages = {"pl.mlipinski.wedding.management.domain.repository"})
@ComponentScan(basePackages = {"pl.mlipinski.wedding.management"})
public class WeddingManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeddingManagementApplication.class, args);
	}
}
