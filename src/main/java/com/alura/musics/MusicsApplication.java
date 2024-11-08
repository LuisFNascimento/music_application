package com.alura.musics;

import com.alura.musics.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MusicsApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();

	}
}
