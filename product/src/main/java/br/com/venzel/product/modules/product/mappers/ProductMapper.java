package br.com.venzel.product.modules.product.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.venzel.product.modules.product.dtos.ResponseProductDTO;
import br.com.venzel.product.modules.product.dtos.ResponseListProductDTO;
import br.com.venzel.product.modules.product.dtos.UpdateProductDTO;
import br.com.venzel.product.modules.product.models.Product;

@Component
public class ProductMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ResponseProductDTO toDTO(Product product) {
        return modelMapper.map(product, ResponseProductDTO.class);
    }

    public ResponseListProductDTO toPageDTO(Product product) {
        return modelMapper.map(product, ResponseListProductDTO.class);
    }

    public Page<ResponseListProductDTO> toCollectionPageModel(Page<Product> products) {
        return products.map(e -> toPageDTO(e));
    }

    public void toCopyEntity(UpdateProductDTO dto, Product product) {
        modelMapper.map(dto, product);
    }
}
