package br.com.venzel.product.modules.product.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.venzel.product.modules.product.dtos.ResponseProductDTO;
import br.com.venzel.product.modules.product.dtos.RequestProductDTO;
import br.com.venzel.product.modules.product.services.CreateProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private CreateProductService createProductService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseProductDTO createProduct(@RequestBody RequestProductDTO req) {
        return createProductService.execute(req);
    }
}