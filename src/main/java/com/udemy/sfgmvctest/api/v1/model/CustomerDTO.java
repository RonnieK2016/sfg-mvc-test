package com.udemy.sfgmvctest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerDTO {

    @JsonIgnore
    Long id;

    @JsonProperty("firstname")
    private String firstName;
    @JsonProperty("lastname")
    private String lastName;

    @JsonProperty("customer_url")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String customerUrl;
}
