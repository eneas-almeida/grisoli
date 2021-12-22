package br.com.venzel.product.modules.product.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.venzel.product.modules.product.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findOneById(Integer id);

    Optional<Product> findOneByName(String name);

    List<Product> findByNameIgnoreCaseContaining(String name);
    
    List<Product> findAllByCategoryId(Integer id);
    
    List<Product> findAllBySupplierId(Integer id);

    Boolean existsByName(String name);

    Boolean existsByCategoryId(Integer id);

    Boolean existsBySupplierId(Integer id);
}
