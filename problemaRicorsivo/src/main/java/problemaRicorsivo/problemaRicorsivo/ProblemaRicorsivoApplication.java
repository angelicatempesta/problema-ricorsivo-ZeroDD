package problemaRicorsivo.problemaRicorsivo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import problemaRicorsivo.problemaRicorsivo.initActivity.ActivityDBPopulation;


@SpringBootApplication(scanBasePackages = "problemaRicorsivo.problemaRicorsivo")
@EnableJpaRepositories("problemaRicorsivo.problemaRicorsivo.repository")
public class ProblemaRicorsivoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProblemaRicorsivoApplication.class, args);
		ActivityDBPopulation.main(new String[]{});
	}

}
