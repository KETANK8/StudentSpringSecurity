package com.studentswagger;

import com.studentswagger.repository.StudentRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableJpaRepositories(basePackageClasses = StudentRepository.class)
@EnableSwagger2
@SpringBootApplication
public class StudentBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentBootApplication.class, args);
	}

}
