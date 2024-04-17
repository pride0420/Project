package com.example.demo.security.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class CorsConfig implements WebMvcConfigurer {
	/*
	 @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/chat/addChat")
	            .allowedOrigins("http://localhost:3000")
	            .allowedMethods("GET", "POST")
	            .allowedHeaders("Content-Type")
	            .allowCredentials(true);
	    }
*/
}
