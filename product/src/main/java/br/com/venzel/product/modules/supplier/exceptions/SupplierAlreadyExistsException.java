package br.com.venzel.product.modules.supplier.exceptions;

import br.com.venzel.product.shared.exceptions.business.EntityAlreadyExistsException;

public class SupplierAlreadyExistsException extends EntityAlreadyExistsException {
    
    private static final long serialVersionUID = 1L;
    
    public SupplierAlreadyExistsException(String message) {
        super(message);
    }
}