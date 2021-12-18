package br.com.venzel.product.shared.exceptions;

import lombok.Getter;

@Getter
public enum ProblemType {
    
    ENTITY_IN_USER("/ENTITY-IN-USE", "Entity in use"),
    ENTITY_NOT_FOUND("/ENTITY-NOT-FOUND", "Entity not found"),
    ENTITY_ALREADY_EXISTS("/ENTITY-ALREADY-EXISTS", "Entity already exists");

    public String uri;
    public String title;

    ProblemType(String path, String title) {
        this.uri = "https://series.app" + path;
        this.title = title;
    }
}