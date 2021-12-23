package br.com.venzel.product.modules.category.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.venzel.product.modules.category.dtos.ResponseCategoryDTO;
import br.com.venzel.product.modules.category.exceptions.CategoryNotFoundException;
import br.com.venzel.product.modules.category.mappers.CategoryMapper;
import br.com.venzel.product.modules.category.models.Category;
import br.com.venzel.product.modules.category.repositories.CategoryRepository;
import br.com.venzel.product.modules.category.utils.CategoryMessageUtil;

@Service
public class ShowCategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ResponseCategoryDTO execute(Integer categoryId) {

        /* Find category by id */

        Optional<Category> optionalEntity = categoryRepository.findById(categoryId);

        /* Guard strategy */

        if (!optionalEntity.isPresent()) {
            throw new CategoryNotFoundException(CategoryMessageUtil.CATEGORY_NOT_FOUND);
        }

        /* convert to dto and return */

        return categoryMapper.toDTO(optionalEntity.get());
    }
}
