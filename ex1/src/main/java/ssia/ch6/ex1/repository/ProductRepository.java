package ssia.ch6.ex1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ssia.ch6.ex1.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
