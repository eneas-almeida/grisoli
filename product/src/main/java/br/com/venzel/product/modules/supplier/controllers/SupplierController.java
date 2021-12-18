package br.com.venzel.product.modules.supplier.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.venzel.product.modules.supplier.dtos.CreateRequestSupplierDTO;
import br.com.venzel.product.modules.supplier.dtos.SupplierDTO;
import br.com.venzel.product.modules.supplier.services.CreateSupplierService;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private CreateSupplierService createSupplierService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SupplierDTO createSupplier(@RequestBody CreateRequestSupplierDTO req) {
        return createSupplierService.execute(req);
    }
}