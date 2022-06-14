package com.DogbirdFoot.AloneBootAws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AloneBootAwsApplication {

	public static void main(String[] args) {

		SpringApplication.run(AloneBootAwsApplication.class, args);
	}

}
