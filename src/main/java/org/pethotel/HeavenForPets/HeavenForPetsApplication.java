package org.pethotel.HeavenForPets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"org.pethotel"})
@EntityScan("org.pethotel.HeavenForPets.entity")
@EnableJpaRepositories("org.pethotel.HeavenForPets.repository")
public class HeavenForPetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeavenForPetsApplication.class, args);
	}
}
