package br.com.venzel.product.modules.product.dtos;

import lombok.Getter;

@Getter
public class RequestProductDTO {
    
    private String name;

    private Long categoryId;

    private Long supplierId;
}
