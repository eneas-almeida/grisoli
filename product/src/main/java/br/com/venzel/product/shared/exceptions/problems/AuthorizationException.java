package br.com.venzel.product.shared.exceptions.problems;

public class AuthorizationException extends EntityException {

    private static final long serialVersionUID = 1L;

    public AuthorizationException(String message) {
        super(message);
    }
}