package br.com.venzel.product.modules.category.mappers;

// import java.util.List;
// import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.venzel.product.modules.category.dtos.ResponseCategoryDTO;
import br.com.venzel.product.modules.category.dtos.ResponsePageCategoryDTO;
import br.com.venzel.product.modules.category.dtos.UpdateCategoryDTO;
import br.com.venzel.product.modules.category.models.Category;

@Component
public class CategoryMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ResponseCategoryDTO toDTO(Category category) {
        return modelMapper.map(category, ResponseCategoryDTO.class);
    }

    public ResponsePageCategoryDTO toPageDTO(Category category) {
        return modelMapper.map(category, ResponsePageCategoryDTO.class);
    }

    public Page<ResponsePageCategoryDTO> toCollectionPageModel(Page<Category> categories) {
        return categories.map(e -> toPageDTO(e));
    }

    public void toCopyEntity(UpdateCategoryDTO dto, Category category) {
        modelMapper.map(dto, category);
    }
}
