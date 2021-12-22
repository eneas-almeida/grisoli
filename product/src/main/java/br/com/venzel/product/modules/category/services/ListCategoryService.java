package br.com.venzel.product.modules.category.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.venzel.product.modules.category.dtos.ResponsePageCategoryDTO;
import br.com.venzel.product.modules.category.exceptions.CategoryNotFoundException;
import br.com.venzel.product.modules.category.mappers.CategoryMapper;
import br.com.venzel.product.modules.category.models.Category;
import br.com.venzel.product.modules.category.repositories.CategoryRepository;
import br.com.venzel.product.modules.category.utils.CategoryMessageUtil;

@Service
public class ListCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;
    
    public Page<ResponsePageCategoryDTO> execute(Integer page, Integer linesPerPage, String orderBy, String direction) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

        Page<Category> categories = categoryRepository.findAll(pageRequest);

        if (categories.isEmpty()) {
            throw new CategoryNotFoundException(CategoryMessageUtil.CATEGORY_NOT_FOUND);
        }

        Page<ResponsePageCategoryDTO> pageCategoryDTO = categoryMapper.toCollectionPageModel(categories);

        return pageCategoryDTO;
    }

}
