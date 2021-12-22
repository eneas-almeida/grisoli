package br.com.venzel.product.modules.supplier.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.venzel.product.modules.supplier.dtos.RequestSupplierDTO;
import br.com.venzel.product.modules.supplier.dtos.ResponseSupplierDTO;
import br.com.venzel.product.modules.supplier.services.CreateSupplierService;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private CreateSupplierService createSupplierService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseSupplierDTO createSupplier(@RequestBody RequestSupplierDTO req) {
        return createSupplierService.execute(req);
    }
}