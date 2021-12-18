package br.com.venzel.product.modules.category.exceptions;

import br.com.venzel.product.shared.exceptions.problems.EntityNotFoundException;

public class CatgoryNotFoundException extends EntityNotFoundException {
    
    private static final long serialVersionUID = 1L;
    
    public CatgoryNotFoundException(String message) {
        super(message);
    }
}
