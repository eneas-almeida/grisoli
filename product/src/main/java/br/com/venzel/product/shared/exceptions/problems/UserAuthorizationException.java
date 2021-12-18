package br.com.venzel.product.shared.exceptions.problems;

public class UserAuthorizationException extends EntityException {

    private static final long serialVersionUID = 1L;

    public UserAuthorizationException(String message) {
        super(message);
    }
}