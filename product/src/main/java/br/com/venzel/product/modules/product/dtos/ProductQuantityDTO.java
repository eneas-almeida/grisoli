package br.com.venzel.product.modules.product.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductQuantityDTO {
    
    private Long productId;
    
    private Integer quantity;
}
