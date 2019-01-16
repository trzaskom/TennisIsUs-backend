package com.trzaskom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TennisIsUsApplication {

	public final static Logger logger = LoggerFactory.getLogger(TennisIsUsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TennisIsUsApplication.class, args);
	}

}

