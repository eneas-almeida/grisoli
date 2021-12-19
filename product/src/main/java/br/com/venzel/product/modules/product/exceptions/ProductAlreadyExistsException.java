package br.com.venzel.product.modules.product.exceptions;

import br.com.venzel.product.shared.exceptions.business.EntityAlreadyExistsException;

public class ProductAlreadyExistsException extends EntityAlreadyExistsException {
    
    private static final long serialVersionUID = 1L;
    
    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}