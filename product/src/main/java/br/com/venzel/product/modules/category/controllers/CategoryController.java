package br.com.venzel.product.modules.category.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.venzel.product.modules.category.dtos.ResponseCategoryDTO;
import br.com.venzel.product.modules.category.dtos.RequestCategoryDTO;
import br.com.venzel.product.modules.category.services.CreateCategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CreateCategoryService createCategoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseCategoryDTO createCategory(@RequestBody RequestCategoryDTO req) {
        return createCategoryService.execute(req);
    }
}