package br.com.venzel.product.modules.product.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.venzel.product.modules.product.dtos.RequestUpdateProductDTO;
import br.com.venzel.product.modules.product.dtos.ResponseListProductDTO;
import br.com.venzel.product.modules.product.dtos.ResponseProductDTO;
import br.com.venzel.product.modules.product.models.Product;

@Component
public class ProductMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ResponseProductDTO toDTO(Product product) {
        return modelMapper.map(product, ResponseProductDTO.class);
    }

    public ResponseListProductDTO toListDTO(Product product) {
        return modelMapper.map(product, ResponseListProductDTO.class);
    }

    public List<ResponseListProductDTO> toCollectionModel(List<Product> categories) {
        return categories.stream()
                    .map(e -> toListDTO(e))
                    .collect(Collectors.toList());
    }

    public Page<ResponseListProductDTO> toCollectionPageModel(Page<Product> products) {
        return products.map(e -> toListDTO(e));
    }

    public void toCopyEntity(RequestUpdateProductDTO dto, Product product) {
        modelMapper.map(dto, product);
    }
}
