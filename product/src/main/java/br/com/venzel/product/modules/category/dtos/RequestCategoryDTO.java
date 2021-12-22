package br.com.venzel.product.modules.category.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCategoryDTO {
 
    @NotBlank
    private String name;
}