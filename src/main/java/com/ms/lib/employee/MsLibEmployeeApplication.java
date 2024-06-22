package com.ms.lib.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = {"com.ms.lib.employee"})
@EnableJpaRepositories(basePackages = {"com.ms.lib.employee"})
@EntityScan(basePackages = {"com.ms.lib.employee"})
public class MsLibEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsLibEmployeeApplication.class, args);
	}

}
