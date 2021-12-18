package br.com.venzel.product.modules.product.dtos;

import br.com.venzel.product.modules.category.dtos.CategoryDTO;
import br.com.venzel.product.modules.supplier.dtos.SupplierDTO;

import lombok.Getter;

@Getter
public class ListProductDTO {
    
    private Long id;

    private String name;

    private CategoryDTO category;

    private SupplierDTO supplier;
}
