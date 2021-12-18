package br.com.venzel.product.modules.product.dtos;

import lombok.Getter;
import lombok.Setter;

import br.com.venzel.product.modules.category.dtos.CategoryDTO;
import br.com.venzel.product.modules.supplier.dtos.SupplierDTO;

@Getter
@Setter
public class UpdateProductDTO {
    
    private String name;

    private CategoryDTO category;

    private SupplierDTO supplier;
}
