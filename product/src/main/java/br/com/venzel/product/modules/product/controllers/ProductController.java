package br.com.venzel.product.modules.product.controllers;

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

import br.com.venzel.product.modules.product.dtos.RequestProductDTO;
import br.com.venzel.product.modules.product.dtos.ResponseListProductDTO;
import br.com.venzel.product.modules.product.dtos.ResponseProductDTO;
import br.com.venzel.product.modules.product.services.CreateProductService;
import br.com.venzel.product.modules.product.services.ListProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private CreateProductService createProductService;

    @Autowired
    private ListProductService listProductService;

    // CREATE

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseProductDTO create(@RequestBody @Valid RequestProductDTO req) {

        return createProductService.execute(req);
    }

    // LIST

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ResponseListProductDTO> list(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                             @RequestParam(value = "linesPerPage", defaultValue = "4") Integer linesPerPage,
                                             @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
                                             @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        return listProductService.execute(page, linesPerPage, orderBy, direction);
    }
}