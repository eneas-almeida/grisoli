package br.com.venzel.product.modules.product.controllers;

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

import br.com.venzel.product.modules.product.dtos.RequestCreateProductDTO;
import br.com.venzel.product.modules.product.dtos.RequestUpdateProductDTO;
import br.com.venzel.product.modules.product.dtos.ResponseListProductDTO;
import br.com.venzel.product.modules.product.dtos.ResponseProductDTO;
import br.com.venzel.product.modules.product.services.CreateProductService;
import br.com.venzel.product.modules.product.services.DeleteProductService;
import br.com.venzel.product.modules.product.services.ListProductService;
import br.com.venzel.product.modules.product.services.ShowProductService;
import br.com.venzel.product.modules.product.services.UpdateProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private CreateProductService createService;

    @Autowired
    private UpdateProductService updateService;

    @Autowired
    private DeleteProductService deleteService;

    @Autowired
    private ListProductService listService;

    @Autowired
    private ShowProductService showService;

    /* CREATE */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseProductDTO create(@RequestBody @Valid RequestCreateProductDTO requestDTO) {
        return createService.execute(requestDTO);
    }

    /* UPDATE */

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseProductDTO updateHandle(@RequestBody @Valid RequestUpdateProductDTO requestDTOuestDTO) {
        return updateService.execute(requestDTOuestDTO);
    } 

    /* LIST */

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ResponseListProductDTO> list(
                @RequestParam(value = "page", defaultValue = "0") Integer page,
                @RequestParam(value = "linesPerPage", defaultValue = "4") Integer linesPerPage,
                @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
                @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return listService.execute(page, linesPerPage, orderBy, direction);
    }

    /* SHOW */

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseProductDTO showHandle(@PathVariable Integer productId) {
        return showService.execute(productId);
    }

    /* DELETE */

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseProductDTO createHandle(@PathVariable Integer productId) {
        return deleteService.execute(productId);
    }
}