package br.com.venzel.product.modules.product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.venzel.product.modules.product.dtos.CreateProductDTO;
import br.com.venzel.product.modules.product.dtos.ProductDTO;
import br.com.venzel.product.modules.product.exceptions.ProductAlreadyExistsException;
import br.com.venzel.product.modules.product.mappers.ProductMapper;
import br.com.venzel.product.modules.product.models.Product;
import br.com.venzel.product.modules.product.repositories.ProductRepository;
import br.com.venzel.product.modules.product.utils.ProductMessageUtil;

@Service
public class CreateProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProductMapper productMapper;

    @Transactional
    public ProductDTO execute(CreateProductDTO dto) {
        /* Verify product existence with name */        

        Boolean existsProduct = productRepository.existsByName(dto.getName());

        /*  Guard strategy */

        if (existsProduct) {
            throw new ProductAlreadyExistsException(ProductMessageUtil.PRODUCT_ALREADY_EXISTS);
        }

        /* Parse dto to entity */

        Product product = productMapper.toEntity(dto);

        /* Save data in repository */

        productRepository.save(product);

        /* Parse entity to dto and return */

        ProductDTO productDTO = productMapper.toDTO(product);

        /* Return product dto */

        return productDTO;
    }
}
