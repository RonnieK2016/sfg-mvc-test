package com.udemy.sfgmvctest.api.v1.mapper;

import com.udemy.sfgmvctest.api.v1.model.CategoryDTO;
import com.udemy.sfgmvctest.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping( target = "categoryUrl", ignore = true)
    CategoryDTO categoryToCategoryDTO(Category category);
}
