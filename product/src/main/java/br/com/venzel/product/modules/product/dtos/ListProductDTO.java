package br.com.venzel.product.modules.product.dtos;

import br.com.venzel.product.modules.category.dtos.ResponseCategoryDTO;
import br.com.venzel.product.modules.supplier.dtos.ResponseSupplierDTO;

import lombok.Getter;

@Getter
public class ListProductDTO {
    
    private Long id;

    private String name;

    private ResponseCategoryDTO category;

    private ResponseSupplierDTO supplier;
}
