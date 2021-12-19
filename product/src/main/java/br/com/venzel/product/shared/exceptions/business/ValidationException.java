package br.com.venzel.product.shared.exceptions.business;

public class ValidationException extends EntityException {

    private static final long serialVersionUID = 1L;

    public ValidationException(String message) {
        super(message);
    }
}