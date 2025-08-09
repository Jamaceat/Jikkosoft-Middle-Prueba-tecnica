package co.com.johan.biblio.gestion_biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @SpringBootApplication(exclude = {SecurityAutoConfiguration.class,ManagementWebSecurityAutoConfiguration.class})
@SpringBootApplication
@ComponentScan("co.com.johan.biblio")
@EnableJpaAuditing
public class GestionBibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionBibliotecaApplication.class, args);
	}

}
