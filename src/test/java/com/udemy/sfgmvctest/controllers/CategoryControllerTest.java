package com.udemy.sfgmvctest.controllers;

import com.udemy.sfgmvctest.api.v1.model.CategoryDTO;
import com.udemy.sfgmvctest.exceptions.ResourceNotFoundException;
import com.udemy.sfgmvctest.services.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest {

    private static final String CATEGORY_NAME = "Fruits";
    private static final Long ID = 1L;
    private static final String API_URL = "/api/v1/categories/";
    private static final String API_URL_WITH_NAME = "/api/v1/categories/" + CATEGORY_NAME;


    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                .setControllerAdvice(new RestExceptionHandler())
                .build();

    }

    @Test
    public void getAllCategories() throws Exception {
        CategoryDTO category1 = new CategoryDTO();
        category1.setId(ID);
        category1.setName(CATEGORY_NAME);

        CategoryDTO category2 = new CategoryDTO();
        category2.setId(2L);
        category2.setName(CATEGORY_NAME);

        List<CategoryDTO> categories = Arrays.asList(category1, category2);

        when(categoryService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(get(API_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)));
    }

    @Test
    public void getCategoryByName() throws Exception {
        CategoryDTO category1 = new CategoryDTO();
        category1.setId(ID);
        category1.setName(CATEGORY_NAME);

        when(categoryService.findCategoryByName(CATEGORY_NAME)).thenReturn(category1);

        mockMvc.perform(get(API_URL_WITH_NAME)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(CATEGORY_NAME)));
    }

    @Test
    public void testGetByNameNotFound() throws Exception {

        when(categoryService.findCategoryByName(anyString())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(API_URL + "/Foo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}