package com.example.springportfolio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

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

}
