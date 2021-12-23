package br.com.venzel.product.modules.category.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.venzel.product.modules.category.dtos.RequestUpdateCategoryDTO;
import br.com.venzel.product.modules.category.dtos.ResponseCategoryDTO;
import br.com.venzel.product.modules.category.dtos.ResponseListCategoryDTO;
import br.com.venzel.product.modules.category.models.Category;

@Component
public class CategoryMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ResponseCategoryDTO toDTO(Category category) {
        return modelMapper.map(category, ResponseCategoryDTO.class);
    }

    public ResponseListCategoryDTO toListDTO(Category category) {
        return modelMapper.map(category, ResponseListCategoryDTO.class);
    }

    public List<ResponseListCategoryDTO> toCollectionModel(List<Category> categories) {
        return categories.stream()
                    .map(e -> toListDTO(e))
                    .collect(Collectors.toList());
    }

    public Page<ResponseListCategoryDTO> toCollectionPageModel(Page<Category> categories) {
        return categories.map(e -> toListDTO(e));
    }

    public void toCopyEntity(RequestUpdateCategoryDTO dto, Category category) {
        modelMapper.map(dto, category);
    }
}
