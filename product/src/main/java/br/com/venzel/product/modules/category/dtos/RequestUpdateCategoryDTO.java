package br.com.venzel.product.modules.category.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class RequestUpdateCategoryDTO {
    
    @NotNull
    private Integer id;

    @NotBlank
    private String name;
}
