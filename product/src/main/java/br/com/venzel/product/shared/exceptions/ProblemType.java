package br.com.venzel.product.shared.exceptions;

import lombok.Getter;

@Getter
public enum ProblemType {
    
    ERRO_IN_SYSTEM("/ERRO-IN-SYSTEM", "Erro in system!"),
    ENTITY_IN_USER("/ENTITY-IN-USE", "Entity in use!"),
    ENTITY_NOT_FOUND("/ENTITY-NOT-FOUND", "Entity not found!"),
    ENTITY_ALREADY_EXISTS("/ENTITY-ALREADY-EXISTS", "Entity already exists!"),
    UNAUTHORIZED("/UNAUTHORIZED", "User not authorized!"),
    VALIDATION("/VALIDATION", "Not valid!");

    public String uri;
    public String title;

    ProblemType(String path, String title) {
        this.uri = "https://product.app" + path;
        this.title = title;
    }
}