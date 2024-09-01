package com.example.DAT250_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Dat250DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Dat250DemoApplication.class, args);
	}

	@GetMapping("/")
	public String helloWorld(){
		return "Hello world!";
	}

}
