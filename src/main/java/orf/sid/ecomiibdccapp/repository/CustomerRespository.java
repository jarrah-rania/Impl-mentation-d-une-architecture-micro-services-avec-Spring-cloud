package orf.sid.ecomiibdccapp.repository;

import orf.sid.ecomiibdccapp.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CustomerRespository extends JpaRepository<Customer,Long> {
}
