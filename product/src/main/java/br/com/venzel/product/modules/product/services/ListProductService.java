package br.com.venzel.product.modules.product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.venzel.product.modules.product.dtos.ResponseListProductDTO;
import br.com.venzel.product.modules.product.exceptions.ProductNotFoundException;
import br.com.venzel.product.modules.product.mappers.ProductMapper;
import br.com.venzel.product.modules.product.models.Product;
import br.com.venzel.product.modules.product.repositories.ProductRepository;
import br.com.venzel.product.modules.product.utils.ProductMessageUtil;

@Service
public class ListProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;
    
    public Page<ResponseListProductDTO> execute(Integer page, Integer linesPerPage, String orderBy, String direction) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

        Page<Product> products = productRepository.findAll(pageRequest);

        if (products.isEmpty()) {
            throw new ProductNotFoundException(ProductMessageUtil.PRODUCT_NOT_FOUND);
        }

        Page<ResponseListProductDTO> pageProductDTO = productMapper.toCollectionPageModel(products);

        return pageProductDTO;
    }

}