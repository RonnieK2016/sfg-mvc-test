package com.udemy.sfgmvctest.services.impl;

import com.udemy.sfgmvctest.api.v1.mapper.CategoryMapper;
import com.udemy.sfgmvctest.api.v1.model.CategoryDTO;
import com.udemy.sfgmvctest.domain.Category;
import com.udemy.sfgmvctest.repositories.CategoryRepository;
import com.udemy.sfgmvctest.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    private CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map( category ->
                {
                    CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);
                    categoryDTO.setCategoryUrl("/api/v1/categories/" + categoryDTO.getName());
                    return categoryDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findCategoryByName(String name) {
        return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
    }
}
