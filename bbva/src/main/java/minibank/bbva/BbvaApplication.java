package minibank.bbva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = TransactionAutoConfiguration.class)
@ImportResource("spring/contexto-jpa-test.xml")
public class BbvaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BbvaApplication.class, args);
	}

}
