package br.com.venzel.product.modules.category.exceptions;

import br.com.venzel.product.shared.exceptions.problems.EntityNotFoundException;

public class CategoryNotFoundException extends EntityNotFoundException {
    
    private static final long serialVersionUID = 1L;
    
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
