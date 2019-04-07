package com.udemy.sfgmvctest.api.v1.mapper;

import com.udemy.sfgmvctest.api.v1.model.VendorDTO;
import com.udemy.sfgmvctest.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    @Mapping(target = "vendorUrl", ignore = true)
    VendorDTO vendorToVendorDTO(Vendor vendor);

    Vendor vendorDtoTOVendor(VendorDTO vendorDTO);
}
