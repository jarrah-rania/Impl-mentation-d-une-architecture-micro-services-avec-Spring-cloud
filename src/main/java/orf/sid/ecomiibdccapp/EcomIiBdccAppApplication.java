package orf.sid.ecomiibdccapp;

import orf.sid.ecomiibdccapp.config.CustomerConfigParams;
import orf.sid.ecomiibdccapp.entities.Customer;
import orf.sid.ecomiibdccapp.repository.CustomerRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class EcomIiBdccAppApplication {

	private static final Logger log =
			LoggerFactory.getLogger(EcomIiBdccAppApplication.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(EcomIiBdccAppApplication.class);

		app.setDefaultProperties(
				java.util.Collections.singletonMap(
						"spring.config.additional-location",
						"file:./config-repo/")
		);

		app.run(args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRespository customerRespository) {
		return args -> {

			customerRespository.save(Customer.builder()
					.name("Rania").email("jarrahrania@gmail.com")
					.build());
			customerRespository.save(Customer.builder()
					.name("Yasmine").email("yas@gmail.com")
					.build());
			customerRespository.save(Customer.builder()
					.name("Yassine").email("yassinetaki@gmail.com")
					.build());

			customerRespository.findAll().forEach(c -> {
				log.info("========== Customer ==========");
				log.info("Id    : {}", c.getId());
				log.info("Name  : {}", c.getName());
				log.info("Email : {}", c.getEmail());
				log.info("==============================");
			});
		};
	}
}
