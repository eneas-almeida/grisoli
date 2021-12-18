package br.com.venzel.product.modules.product.dtos;

import br.com.venzel.product.modules.category.dtos.CategoryDTO;
import br.com.venzel.product.modules.supplier.dtos.SupplierDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductDTO {
    
    private String name;

    private CategoryDTO category;

    private SupplierDTO supplier;
}
