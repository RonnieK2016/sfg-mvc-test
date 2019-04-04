package com.udemy.sfgmvctest.api.v1.model;

import com.udemy.sfgmvctest.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryListDTO {

    List<CategoryDTO> categories;

}
