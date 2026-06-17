package com.apiAquivos.inputAquivos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InputAquivosApplication {

	public static void main(String[] args) {
		SpringApplication.run(InputAquivosApplication.class, args);
	}

}
