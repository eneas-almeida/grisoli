package br.com.venzel.product.modules.product.exceptions;

import br.com.venzel.product.shared.exceptions.business.EntityInUseException;

public class ProductInUseException extends EntityInUseException {
    
    private static final long serialVersionUID = 1L;
    
    public ProductInUseException(String message) {
        super(message);
    }
}