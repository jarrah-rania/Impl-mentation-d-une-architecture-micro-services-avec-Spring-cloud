package org.sid.iventoryservice;

import org.sid.iventoryservice.entities.Product;
import org.sid.iventoryservice.repository.ProductRespository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.UUID;

@SpringBootApplication
public class IventoryServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(IventoryServiceApplication.class, args);
    }
   @Bean
    CommandLineRunner commandLineRunner(ProductRespository productRespository)
   {
       return args -> {
           productRespository.save(Product.builder()
                   .id(UUID.randomUUID().toString())
                           .name("Computer")
                           .price(3200)
                           .quantity(11)
                           .build());

           productRespository.save(Product.builder()
                   .id(UUID.randomUUID().toString())
                   .name("Printer")
                   .price(1200)
                   .quantity(13)
                   .build());
           productRespository.save(Product.builder()
                   .id(UUID.randomUUID().toString())
                   .name("Smart phone")
                   .price(6000)
                   .quantity(32)
                   .build());

           productRespository.findAll().forEach(p->{
               System.out.println(p.toString());
           });
       };

   }

}
