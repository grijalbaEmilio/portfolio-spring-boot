package com.example.springportfolio;

import com.example.springportfolio.model.Comment;
import com.example.springportfolio.model.Role;
import com.example.springportfolio.model.User;
import com.example.springportfolio.reposiroties.CommentRepository;
import com.example.springportfolio.reposiroties.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class SpringPortfolioApplication {

	// TODO: authentication all end points with jwt
	public static void main(String[] args) {
		SpringApplication.run(SpringPortfolioApplication.class, args);
	}

}
