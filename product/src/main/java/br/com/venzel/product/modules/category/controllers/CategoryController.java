package br.com.venzel.product.modules.category.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.venzel.product.modules.category.dtos.RequestCreateCategoryDTO;
import br.com.venzel.product.modules.category.dtos.RequestUpdateCategoryDTO;
import br.com.venzel.product.modules.category.dtos.ResponseCategoryDTO;
import br.com.venzel.product.modules.category.dtos.ResponseListCategoryDTO;
import br.com.venzel.product.modules.category.services.CreateCategoryService;
import br.com.venzel.product.modules.category.services.DeleteCategoryService;
import br.com.venzel.product.modules.category.services.ListCategoryService;
import br.com.venzel.product.modules.category.services.ShowCategoryService;
import br.com.venzel.product.modules.category.services.UpdateCategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CreateCategoryService createService;

    @Autowired
    private UpdateCategoryService updateService;

    @Autowired
    private DeleteCategoryService deleteService;

    @Autowired
    private ListCategoryService listService;

    @Autowired
    private ShowCategoryService showService;

    /* CREATE */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseCategoryDTO create(@RequestBody @Valid RequestCreateCategoryDTO requestDTO) {
        return createService.execute(requestDTO);
    }

    /* UPDATE */

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseCategoryDTO updateHandle(@RequestBody @Valid RequestUpdateCategoryDTO requestDTOuestDTO) {
        return updateService.execute(requestDTOuestDTO);
    } 

    /* LIST */

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ResponseListCategoryDTO> list(
                @RequestParam(value = "page", defaultValue = "0") Integer page,
                @RequestParam(value = "linesPerPage", defaultValue = "4") Integer linesPerPage,
                @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
                @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return listService.execute(page, linesPerPage, orderBy, direction);
    }

    /* SHOW */

    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseCategoryDTO showHandle(@PathVariable Integer categoryId) {
        return showService.execute(categoryId);
    }

    /* DELETE */

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseCategoryDTO createHandle(@PathVariable Integer categoryId) {
        return deleteService.execute(categoryId);
    }
}