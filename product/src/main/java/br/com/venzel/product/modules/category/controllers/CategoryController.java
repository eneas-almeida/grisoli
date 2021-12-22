package br.com.venzel.product.modules.category.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.venzel.product.modules.category.dtos.RequestCategoryDTO;
import br.com.venzel.product.modules.category.dtos.ResponseCategoryDTO;
import br.com.venzel.product.modules.category.dtos.ResponsePageCategoryDTO;
import br.com.venzel.product.modules.category.services.CreateCategoryService;
import br.com.venzel.product.modules.category.services.ListCategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CreateCategoryService createCategoryService;

    @Autowired
    private ListCategoryService listCategoryService;

    // CREATE

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseCategoryDTO create(@RequestBody @Valid RequestCategoryDTO req) {

        return createCategoryService.execute(req);
    }

    // LIST

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ResponsePageCategoryDTO> list(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                              @RequestParam(value = "linesPerPage", defaultValue = "4") Integer linesPerPage,
                                              @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
                                              @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        return listCategoryService.execute(page, linesPerPage, orderBy, direction);
    }
}