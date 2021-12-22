package br.com.venzel.product.modules.product.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import br.com.venzel.product.modules.category.models.Category;
import br.com.venzel.product.modules.supplier.models.Supplier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer quantityAvailable;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public static Product create(String name, Integer quantityAvailable, Category category, Supplier supplier) {
        return Product
            .builder()
            .name(name)
            .quantityAvailable(quantityAvailable)
            .category(category)
            .supplier(supplier)
            .build();
    }

    public void updateStock(Integer quantity) {
        quantityAvailable = quantityAvailable - quantity;
    }
}