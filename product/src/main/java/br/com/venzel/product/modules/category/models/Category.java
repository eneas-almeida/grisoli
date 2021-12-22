package br.com.venzel.product.modules.category.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import br.com.venzel.product.modules.product.models.Product;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    
    @Column(nullable = false)
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();

    public static Category create(String name) {
        return Category
            .builder()
            .name(name)
            .build();
    }
}