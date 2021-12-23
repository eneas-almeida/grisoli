package br.com.venzel.product.modules.category.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.venzel.product.modules.category.dtos.ResponseCategoryDTO;
import br.com.venzel.product.modules.category.exceptions.CategoryNotFoundException;
import br.com.venzel.product.modules.category.mappers.CategoryMapper;
import br.com.venzel.product.modules.category.models.Category;
import br.com.venzel.product.modules.category.repositories.CategoryRepository;
import br.com.venzel.product.modules.category.utils.CategoryMessageUtil;

@Service
public class DeleteCategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private CategoryMapper categoryMapper;

    @Transactional
    public ResponseCategoryDTO execute(Integer cateogoryId) {

        /* Find category by name */

        Optional<Category> optionalEntity = categoryRepository.findOneById(cateogoryId);

        /* Guard strategy */

        if (optionalEntity.isEmpty()) {
            throw new CategoryNotFoundException(CategoryMessageUtil.CATEGORY_NOT_FOUND);
        }

        /* Get object */

        Category category = optionalEntity.get();

        /* Delete category */

        categoryRepository.deleteById(cateogoryId);

        /* Convert objeto to dto and return */

        return categoryMapper.toDTO(category);
    }
}
