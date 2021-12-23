package br.com.venzel.product.modules.supplier.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUpdateSupplierDTO {

    @NotNull
    private Integer id;
    
    @NotBlank
    private String name;
}
