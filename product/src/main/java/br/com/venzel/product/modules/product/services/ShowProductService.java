package br.com.venzel.product.modules.product.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.venzel.product.modules.product.dtos.ResponseProductDTO;
import br.com.venzel.product.modules.product.exceptions.ProductNotFoundException;
import br.com.venzel.product.modules.product.mappers.ProductMapper;
import br.com.venzel.product.modules.product.models.Product;
import br.com.venzel.product.modules.product.repositories.ProductRepository;
import br.com.venzel.product.modules.product.utils.ProductMessageUtil;

@Service
public class ShowProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ResponseProductDTO execute(Integer productId) {

        /* Find product by id */

        Optional<Product> optionalEntity = productRepository.findById(productId);

        /* Guard strategy */

        if (!optionalEntity.isPresent()) {
            throw new ProductNotFoundException(ProductMessageUtil.PRODUCT_NOT_FOUND);
        }

        /* convert to dto and return */

        return productMapper.toDTO(optionalEntity.get());
    }
}
