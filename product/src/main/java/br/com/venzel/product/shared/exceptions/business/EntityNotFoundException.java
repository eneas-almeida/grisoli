package br.com.venzel.product.shared.exceptions.business;

public class EntityNotFoundException extends EntityException {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message) {
        super(message);
    }
}