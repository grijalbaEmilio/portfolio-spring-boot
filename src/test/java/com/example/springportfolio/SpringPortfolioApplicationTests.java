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

	String jwtSecret="fritjlsdljf__4ritsi7788";
	int jwtExpirationMs =86400000;

	enum Nn{ ADMIN, USER};

	@Test
	void generateJwtToken() {

		// UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		User user = new User(null, "luis","grialba", "luis@gmial.com","contraseñita", Role.ADMIN);
		HashMap<String, Object> payload = new HashMap<>();
		payload.put("id", user.getId());
		payload.put("name", user.getName());
		payload.put("lastname", user.getLastName());
		payload.put("email", user.getEmail());
		payload.put("password", user.getPassword());
		payload.put("role", user.getRole());

		var token = Jwts.builder()
				.setClaims(payload)
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();

		System.out.println(token);
		token = "eyJhbGciOiJIUzUxMiJ9.eyJwYXNzd29yZCI6IiQyYSQxMCRsTG5mQk5mSU1lRGplaU9MZXY2eVBlRklTVGRaL0hTeDhOMEtDaXhJNU12NVAuMFNheVJmLiIsInJvbGUiOiJSRVZJRVdFUiIsIm5hbWUiOiJlbCDDum5pY28gcmV2aXNvciIsImlkIjoxMSwiZXhwIjoxNjY3OTU4NTUwLCJpYXQiOjE2Njc4NzIxNTAsImVtYWlsIjoidGVzdFRva2VuQGdtYWlsLmNvbSIsImxhc3RuYW1lIjoiR3JpamFsYmEifQ.uH6qNaxul2ixevNwliv2m5oEFA0oIsCzHUFOrPS8G8rUAdWMVt-ghnryZ3z89T-IDfzDfsIHtegHKpRRHEgrjg";
		var subject = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		Integer id = (Integer) subject.get("id");
		Long idL = id.longValue();
		User users = new User(idL, "luis","grialba", "luis@gmial.com","contraseñita", Role.ADMIN);
		System.out.println(subject);
	}

}
