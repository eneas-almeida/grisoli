package br.com.venzel.product.modules.category.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.venzel.product.modules.category.dtos.ResponseCategoryDTO;
import br.com.venzel.product.modules.category.dtos.ListCategoryDTO;
import br.com.venzel.product.modules.category.dtos.UpdateCategoryDTO;
import br.com.venzel.product.modules.category.models.Category;

@Component
public class CategoryMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ResponseCategoryDTO toDTO(Category category) {
        return modelMapper.map(category, ResponseCategoryDTO.class);
    }

    public ListCategoryDTO toListDTO(Category category) {
        return modelMapper.map(category, ListCategoryDTO.class);
    }

    public List<ListCategoryDTO> toCollectionModel(List<Category> categorys) {
        return categorys.stream()
                    .map(e -> toListDTO(e))
                    .collect(Collectors.toList());
    }

    public Page<ListCategoryDTO> toCollectionPageModel(Page<Category> categorys) {
        return categorys.map(e -> toListDTO(e));
    }

    public void toCopyEntity(UpdateCategoryDTO dto, Category category) {
        modelMapper.map(dto, category);
    }
}
