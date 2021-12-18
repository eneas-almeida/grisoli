package br.com.venzel.product.modules.product.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.venzel.product.modules.product.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findOneById(Long id);

    Optional<Product> findOneByName(String name);

    Boolean existsByName(String name);
}
