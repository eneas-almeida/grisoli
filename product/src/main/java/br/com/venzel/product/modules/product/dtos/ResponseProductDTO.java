package br.com.venzel.product.modules.product.dtos;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.venzel.product.modules.category.dtos.ResponseCategoryDTO;
import br.com.venzel.product.modules.supplier.dtos.ResponseSupplierDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseProductDTO {
    
    private Long id;

    private String name;

    @JsonManagedReference
    private ResponseCategoryDTO category;

    @JsonManagedReference
    private ResponseSupplierDTO supplier;
}