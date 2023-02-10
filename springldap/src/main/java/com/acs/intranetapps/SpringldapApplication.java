package com.acs.intranetapps;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.acs.intranetapps.repo.PersonRepository;

@SpringBootApplication
public class SpringldapApplication {
	private static Logger log = LoggerFactory.getLogger(SpringldapApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringldapApplication.class, args);
	}

	@Autowired
	private PersonRepository personRepository;

	@PostConstruct
	public void setup() {
		log.info("Spring LDAP + Spring Boot Configuration Example");

		List<String> names = personRepository.getAllPersonNames();
		log.info("names: " + names);

	}
}
