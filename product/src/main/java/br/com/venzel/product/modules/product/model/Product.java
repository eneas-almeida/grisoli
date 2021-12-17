package br.com.venzel.product.modules.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    private Long id;
    
    @Column()
    private String name;

    @ManyToOne
    @JoinColumn(name = "FK_CATEGORY", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "FK_SUPPLIER", nullable = false)
    private Supplier supplier;

    public Product(String name, Category category, Supplier supplier) {
        super();
        this.name = name;
        this.category = category;
        this.supplier = supplier;
    }

    public static Product create(String name, Category category, Supplier supplier) {
        return new Product(name, category, supplier);
    }
}