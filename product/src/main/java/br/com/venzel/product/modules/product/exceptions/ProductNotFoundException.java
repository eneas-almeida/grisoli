package br.com.venzel.product.modules.product.exceptions;

import br.com.venzel.product.shared.exceptions.business.EntityNotFoundException;

public class ProductNotFoundException extends EntityNotFoundException {
    
    private static final long serialVersionUID = 1L;
    
    public ProductNotFoundException(String message) {
        super(message);
    }
}
