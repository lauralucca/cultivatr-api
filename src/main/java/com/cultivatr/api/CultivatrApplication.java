package com.cultivatr.api;

import com.cultivatr.api.utils.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication
@RestController
public class CultivatrApplication {

	public static void main(String[] args) {
		SpringApplication.run(CultivatrApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/hello").allowedOrigins(Constants.frontDevURL);
				registry.addMapping("/hello").allowedOrigins(Constants.frontProdURL);
				registry.addMapping("/user").allowedOrigins(Constants.frontDevURL);;
				registry.addMapping("/user").allowedOrigins(Constants.frontProdURL);
			}
		};
	}
}





