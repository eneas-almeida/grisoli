package br.com.venzel.product.modules.product.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.venzel.product.modules.product.dtos.ResponseProductDTO;
import br.com.venzel.product.modules.product.exceptions.ProductNotFoundException;
import br.com.venzel.product.modules.product.mappers.ProductMapper;
import br.com.venzel.product.modules.product.models.Product;
import br.com.venzel.product.modules.product.repositories.ProductRepository;
import br.com.venzel.product.modules.product.utils.ProductMessageUtil;

@Service
public class DeleteProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProductMapper productMapper;

    @Transactional
    public ResponseProductDTO execute(Integer productId) {

        /* Find product by name */

        Optional<Product> optionalEntity = productRepository.findOneById(productId);

        /* Guard strategy */

        if (optionalEntity.isEmpty()) {
            throw new ProductNotFoundException(ProductMessageUtil.PRODUCT_NOT_FOUND);
        }

        /* Get object */

        Product product = optionalEntity.get();

        /* Delete product */

        productRepository.deleteById(productId);

        /* Convert objeto to dto and return */

        return productMapper.toDTO(product);
    }
}
