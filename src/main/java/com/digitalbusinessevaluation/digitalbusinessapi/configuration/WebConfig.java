package com.digitalbusinessevaluation.digitalbusinessapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.digitalbusinessevaluation.digitalbusinessapi.configuration.pojo.SmartLocaleResolver;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public SmartLocaleResolver localeResolver() {
		return new SmartLocaleResolver();
	}
	

}
