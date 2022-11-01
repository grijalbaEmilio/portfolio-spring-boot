package com.example.springportfolio;

import com.example.springportfolio.model.Role;
import com.example.springportfolio.model.User;
import com.example.springportfolio.reposiroties.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringPortfolioApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(SpringPortfolioApplication.class, args);
		UserRepository userRepo = context.getBean(UserRepository.class);

		/*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode("luis");

		User ll = new User()
						.setName("sin rol")
						.setLastName("Grijalba")
						.setEmail("luiS@gmail.com")
						.setRole("ADMIN")
						.setPassword(password);

		//userRepo.save(ll);
		System.out.println(passwordEncoder.matches("luis", ll.getPassword()));
		System.out.println(passwordEncoder.matches("Luis", ll.getPassword()));
		System.out.println(passwordEncoder.matches("LUIS", ll.getPassword()));
		System.out.println(passwordEncoder.matches("PEPE", ll.getPassword()));
		System.out.println(userRepo.findAll());*/
	}

}
