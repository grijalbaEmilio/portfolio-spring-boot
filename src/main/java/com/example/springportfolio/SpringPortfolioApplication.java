package com.example.springportfolio;

import com.example.springportfolio.reposiroties.ProjectRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringPortfolioApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(SpringPortfolioApplication.class, args);
		ProjectRepository service = context.getBean(ProjectRepository.class);
		System.out.println(service.findAll());
	}

}
