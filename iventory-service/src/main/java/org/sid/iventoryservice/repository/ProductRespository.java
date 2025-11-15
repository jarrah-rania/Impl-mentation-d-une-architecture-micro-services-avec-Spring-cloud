package org.sid.iventoryservice.repository;

import org.sid.iventoryservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")

@RepositoryRestResource
public interface ProductRespository extends JpaRepository<Product,String>
{


}
