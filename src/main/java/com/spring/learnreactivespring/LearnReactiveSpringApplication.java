package com.spring.learnreactivespring;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Collection;
import java.util.Collections;

@SpringBootApplication(
		scanBasePackages = {
		"com.spring.learnreactivespring"
		}
)
@OpenAPIDefinition(info = @Info(title = "LearnReactiveSpring", version = "1.0", description = "Documentation APIs v1.0"))
public class LearnReactiveSpringApplication {

	public static void main(String[] args) {
		SpringApplication app=new SpringApplication(LearnReactiveSpringApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port",System.getProperty("server.port","8090")));
		app.run(args);
	}

}
