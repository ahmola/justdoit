package com.park.fullstack3backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Fullstack3BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(Fullstack3BackendApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer webMvcConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("GET", "POST", "POST", "DELETE")
						.allowedOrigins("http://localhost:3000");
			}
		};
	}
}
