package br.com.venzel.product.modules.supplier.exceptions;

import br.com.venzel.product.shared.exceptions.problems.EntityNotFoundException;

public class SupplierNotFoundException extends EntityNotFoundException {
    
    private static final long serialVersionUID = 1L;
    
    public SupplierNotFoundException(String message) {
        super(message);
    }
}
