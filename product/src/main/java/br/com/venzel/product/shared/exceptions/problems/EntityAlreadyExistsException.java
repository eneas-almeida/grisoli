package br.com.venzel.product.shared.exceptions.problems;

public class EntityAlreadyExistsException extends EntityException {

    private static final long serialVersionUID = 1L;

    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}