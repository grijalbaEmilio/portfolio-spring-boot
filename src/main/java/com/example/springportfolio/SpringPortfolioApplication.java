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

	public static void main(String[] args) {
		var context = SpringApplication.run(SpringPortfolioApplication.class, args);
		/*UserRepository userRepo = context.getBean(UserRepository.class);
		CommentRepository commentRepo = context.getBean(CommentRepository.class);
		User u1 = userRepo.findAll().get(0);
		Comment parent = commentRepo.findAll().get(0);

		commentRepo.save(new Comment(null, "content with parent",new Date(), parent, u1));
		System.out.println(commentRepo.findAll());*/


	}

}
