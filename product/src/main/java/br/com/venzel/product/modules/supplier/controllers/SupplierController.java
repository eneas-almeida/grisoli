package br.com.venzel.product.modules.supplier.controllers;

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

import br.com.venzel.product.modules.supplier.dtos.RequestCreateSupplierDTO;
import br.com.venzel.product.modules.supplier.dtos.RequestUpdateSupplierDTO;
import br.com.venzel.product.modules.supplier.dtos.ResponseListSupplierDTO;
import br.com.venzel.product.modules.supplier.dtos.ResponseSupplierDTO;
import br.com.venzel.product.modules.supplier.services.CreateSupplierService;
import br.com.venzel.product.modules.supplier.services.DeleteSupplierService;
import br.com.venzel.product.modules.supplier.services.ListSupplierService;
import br.com.venzel.product.modules.supplier.services.ShowSupplierService;
import br.com.venzel.product.modules.supplier.services.UpdateSupplierService;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private CreateSupplierService createService;

    @Autowired
    private UpdateSupplierService updateService;

    @Autowired
    private DeleteSupplierService deleteService;

    @Autowired
    private ListSupplierService listService;

    @Autowired
    private ShowSupplierService showService;

    /* CREATE */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseSupplierDTO createHandle(@RequestBody @Valid RequestCreateSupplierDTO requestDTO) {
        return createService.execute(requestDTO);
    }

    /* UPDATE */

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseSupplierDTO updateHandle(@RequestBody @Valid RequestUpdateSupplierDTO requestDTO) {
        return updateService.execute(requestDTO);
    } 

    /* LIST */

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ResponseListSupplierDTO> listHandle(
                @RequestParam(value = "page", defaultValue = "0") Integer page,
                @RequestParam(value = "linesPerPage", defaultValue = "4") Integer linesPerPage,
                @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
                @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return listService.execute(page, linesPerPage, orderBy, direction);
    }

    /* SHOW */

    @GetMapping("/{supplierId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseSupplierDTO showHandle(@PathVariable Integer supplierId) {
        return showService.execute(supplierId);
    }

    /* DELETE */

    @DeleteMapping("/{supplierId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseSupplierDTO createHandle(@PathVariable Integer supplierId) {
        return deleteService.execute(supplierId);
    }
}