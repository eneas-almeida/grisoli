package br.com.venzel.product.modules.supplier.exceptions;

import br.com.venzel.product.shared.exceptions.problems.EntityInUseException;

public class SupplierInUseException extends EntityInUseException {
    
    private static final long serialVersionUID = 1L;
    
    public SupplierInUseException(String message) {
        super(message);
    }
}