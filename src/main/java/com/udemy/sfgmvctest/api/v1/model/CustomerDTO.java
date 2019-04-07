package com.udemy.sfgmvctest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomerDTO {

    @JsonIgnore
    Long id;

    @JsonProperty("firstname")
    @ApiModelProperty(value = "Customer First Name", required = true)
    private String firstName;
    @ApiModelProperty(value = "Customer Last Name", required = true)
    @JsonProperty("lastname")
    private String lastName;

    @JsonProperty("customer_url")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String customerUrl;
}
