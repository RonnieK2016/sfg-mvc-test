package com.udemy.sfgmvctest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
public class CategoryDTO {

    @JsonIgnore
    private Long id;
    private String name;

    @JsonProperty("category_url")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String categoryUrl;
}
