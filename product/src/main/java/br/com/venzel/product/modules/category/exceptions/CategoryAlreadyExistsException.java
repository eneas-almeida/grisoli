package br.com.venzel.product.modules.category.exceptions;

import br.com.venzel.product.shared.exceptions.business.EntityAlreadyExistsException;

public class CategoryAlreadyExistsException extends EntityAlreadyExistsException {
    
    private static final long serialVersionUID = 1L;
    
    public CategoryAlreadyExistsException(String message) {
        super(message);
    }
}