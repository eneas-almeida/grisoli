package br.com.venzel.product.modules.supplier.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCreateSupplierDTO {
    
    @NotBlank
    private String name;
}
