package com.exercicio02videoLocadora.VideoLocadora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VideoLocadoraApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoLocadoraApplication.class, args);
	}

}
