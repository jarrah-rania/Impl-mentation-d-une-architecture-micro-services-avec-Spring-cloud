package orf.sid.ecomiibdccapp;

import orf.sid.ecomiibdccapp.entities.Customer;
import orf.sid.ecomiibdccapp.repository.CustomerRespository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcomIiBdccAppApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(EcomIiBdccAppApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRespository customerRespository){
		return args ->{

			customerRespository.save(Customer.builder()
					.name("Rania").email("jarrahrania@gmail.com")
					.build());
			customerRespository.save(Customer.builder()
					.name("Yasmine").email("yas@gmail.com")
					.build());
			customerRespository.save(Customer.builder()
					.name("Yassine").email("yassinetaki@gmail.com")
					.build());
			customerRespository.findAll().forEach(c->{
						System.out.println("====================");
						System.out.println(c.getId());
						System.out.println(c.getName());
						System.out.println(c.getEmail());
						System.out.println("====================");
					}

			);
		};
	}

}
