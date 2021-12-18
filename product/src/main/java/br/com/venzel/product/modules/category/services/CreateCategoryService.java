package br.com.venzel.product.modules.category.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.venzel.product.modules.category.dtos.CategoryDTO;
import br.com.venzel.product.modules.category.dtos.CreateRequestCategoryDTO;
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
    public CategoryDTO execute(CreateRequestCategoryDTO req) {
        
        /* Verify category existence with name */        

        Boolean existsCatgory = categoryRepository.existsByName(req.getName());

        /*  Guard strategy */

        if (existsCatgory) {
            throw new CategoryAlreadyExistsException(CategoryMessageUtil.CATEGORY_ALREADY_EXISTS);
        }

        /* Create object */

        Category category = Category.create(req.getName());

        /* Save data in repository */

        Category categorySaved = categoryRepository.save(category);

        /* Parse entity to dto */

        CategoryDTO categoryDTO = categoryMapper.toDTO(categorySaved);

        /* Return category dto */

        return categoryDTO;
    }
}
