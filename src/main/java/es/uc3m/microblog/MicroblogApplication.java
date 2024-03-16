package es.uc3m.microblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroblogApplication {

	public static void main(String[] args) {
		/* 
		 * Esta clase arranca el entorno de autoconfiguración que Spring Boot proporciona para la aplicación.
		 */
		SpringApplication.run(MicroblogApplication.class, args);
	}
}
