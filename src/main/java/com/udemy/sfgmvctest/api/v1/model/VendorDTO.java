package com.udemy.sfgmvctest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VendorDTO {
    @JsonIgnore
    private Long id;

    @ApiModelProperty("Vendor Name")
    private String name;

    @JsonProperty("vendor_url")
    private String vendorUrl;
}
