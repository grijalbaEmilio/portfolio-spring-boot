package com.example.springportfolio;

import com.example.springportfolio.model.Role;
import com.example.springportfolio.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

//@SpringBootTest
class SpringPortfolioApplicationTests {

	@Test
	void sum(){
		Long num = 5L;
		System.out.println(num == 5);
	}
	@Test
	void contextLoads() {
		String tecnologies = "   node vue jwt ";
		System.out.println(Arrays.stream(tecnologies.split(" ")).filter(x -> x.length() > 0).toList());
	}

	@Test
	void descomponeNameImage(){

		String imageUrl = "localhost:5000/gitflow-workflow.jp";
		String imageName = Arrays
				.stream(imageUrl.split("/"))
				.reduce("", (x, y)->y);

		System.out.println(imageName);
	}

	String jwtSecret="openb";
	int jwtExpirationMs =86400000;

	enum Nn{ ADMIN, USER};

	@Test
	void generateJwtToken() {

		// UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		User us = new User(null, "luis","grialba", "luis@gmial.com","contraseñita", Role.ADMIN);
		Map usm = new HashMap<String, Object>();
		usm.put("id", us.getId());
		usm.put("name", us.getName());
		usm.put("email", us.getEmail());
		usm.put("password", us.getPassword());
		//var token = Jwts.header(usm).
		var token = Jwts.builder()
				.setSubject(us.getName())
				.setSubject(us.getPassword())
				.setClaims(usm)
				//.setPayload(us.getUsername())
				.setIssuedAt(new Date())
				//.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS384, jwtSecret)
				.compact();

		System.out.println(token);
		var subject = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		System.out.println(subject);
	}

}
