package br.com.venzel.product.modules.category.exceptions;

import br.com.venzel.product.shared.exceptions.business.EntityInUseException;

public class CategoryInUseException extends EntityInUseException {
    
    private static final long serialVersionUID = 1L;
    
    public CategoryInUseException(String message) {
        super(message);
    }
}