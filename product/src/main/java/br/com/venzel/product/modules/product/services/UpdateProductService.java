package br.com.venzel.product.modules.product.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.venzel.product.modules.product.dtos.RequestUpdateProductDTO;
import br.com.venzel.product.modules.product.dtos.ResponseProductDTO;
import br.com.venzel.product.modules.product.exceptions.ProductNotFoundException;
import br.com.venzel.product.modules.product.mappers.ProductMapper;
import br.com.venzel.product.modules.product.models.Product;
import br.com.venzel.product.modules.product.repositories.ProductRepository;
import br.com.venzel.product.modules.product.utils.ProductMessageUtil;

@Service
public class UpdateProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Transactional
    public ResponseProductDTO execute(RequestUpdateProductDTO requestDTO) {

        /* Find product by id */

        Optional<Product> optionalEntity = productRepository.findById(requestDTO.getId());

        /* Guard strategy */

        if (!optionalEntity.isPresent()) {
            throw new ProductNotFoundException(ProductMessageUtil.PRODUCT_NOT_FOUND);
        }

        /* Get object */

        Product product = optionalEntity.get();

        /* Update object */

        product.setName(requestDTO.getName());

        /* Convert to dto and return */

        return productMapper.toDTO(product);
    }
}
