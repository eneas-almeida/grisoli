package br.com.venzel.product.modules.category.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.venzel.product.modules.category.dtos.CategoryDTO;
import br.com.venzel.product.modules.category.dtos.CreateCategoryDTO;
import br.com.venzel.product.modules.category.exceptions.CategoryAlreadyExistsException;
import br.com.venzel.product.modules.category.mappers.CategoryMapper;
import br.com.venzel.product.modules.category.models.Category;
import br.com.venzel.product.modules.category.repositories.CategoryRepository;
import br.com.venzel.product.modules.category.utils.CategoryMessageUtil;

@Service
public class CreateCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private CategoryMapper categoryMapper;

    @Transactional
    public CategoryDTO execute(CreateCategoryDTO dto) {
        /* Verify category existence with name */        

        Boolean existsCatgory = categoryRepository.existsByName(dto.getName());

        /*  Guard strategy */

        if (existsCatgory) {
            throw new CategoryAlreadyExistsException(CategoryMessageUtil.CATEGORY_ALREADY_EXISTS);
        }

        /* Parse dto to entity */

        Category category = categoryMapper.toEntity(dto);

        /* Save data in repository */

        categoryRepository.save(category);

        /* Parse entity to dto and return */

        CategoryDTO categoryDTO = categoryMapper.toDTO(category);

        /* Return category dto */

        return categoryDTO;
    }
}
