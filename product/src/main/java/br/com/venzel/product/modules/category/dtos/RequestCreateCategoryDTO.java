package br.com.venzel.product.modules.category.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class RequestCreateCategoryDTO {
 
    @NotBlank
    private String name;
}