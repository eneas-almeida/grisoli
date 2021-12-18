package br.com.venzel.product.modules.supplier.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.venzel.product.modules.supplier.models.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findOneById(Long id);

    Optional<Supplier> findOneByName(String name);

    Boolean existsByName(String name);
}
