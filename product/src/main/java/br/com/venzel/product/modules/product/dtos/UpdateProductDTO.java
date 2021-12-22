package br.com.venzel.product.modules.product.dtos;

import br.com.venzel.product.modules.category.dtos.ResponseCategoryDTO;
import br.com.venzel.product.modules.supplier.dtos.ResponseSupplierDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductDTO {
    
    private String name;

    private ResponseCategoryDTO category;

    private ResponseSupplierDTO supplier;
}
