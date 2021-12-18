package br.com.venzel.product.modules.category.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.venzel.product.modules.category.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findOneById(Long id);

    Optional<Category> findOneByName(String name);

    Boolean existsByName(String name);
}