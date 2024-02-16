package com.rafael.lojaionic.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.rafael.lojaionic.services.DBService;
import com.rafael.lojaionic.services.EmailService;
import com.rafael.lojaionic.services.MockEmaiilService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbServi;
	
	@Bean
	public boolean instantiateDataBase() throws ParseException {
		
		dbServi.instantiateTestDatabase();
		
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmaiilService();
	}

}
