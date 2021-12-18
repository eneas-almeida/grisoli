package br.com.venzel.product.modules.product.dtos;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.venzel.product.modules.category.dtos.CategoryDTO;
import br.com.venzel.product.modules.supplier.dtos.SupplierDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    
    private Long id;

    private String name;

    @JsonManagedReference
    private CategoryDTO category;

    @JsonManagedReference
    private SupplierDTO supplier;
}