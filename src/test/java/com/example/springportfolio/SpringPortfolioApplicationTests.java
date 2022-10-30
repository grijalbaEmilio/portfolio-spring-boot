package com.example.springportfolio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

//@SpringBootTest
class SpringPortfolioApplicationTests {

	@Test
	void contextLoads() {
		String tecnologies = "   node vue jwt ";
		System.out.println(Arrays.stream(tecnologies.split(" ")).filter(x -> x.length() > 0).toList());
	}

}
