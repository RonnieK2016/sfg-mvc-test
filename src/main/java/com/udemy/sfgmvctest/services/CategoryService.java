package com.udemy.sfgmvctest.services;

import com.udemy.sfgmvctest.api.v1.model.CategoryDTO;
import com.udemy.sfgmvctest.domain.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO findCategoryByName(String name);
}
