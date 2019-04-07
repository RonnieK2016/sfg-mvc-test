package com.udemy.sfgmvctest.services;

import com.udemy.sfgmvctest.api.v1.model.VendorDTO;

import java.util.List;

public interface VendorService {

    List<VendorDTO> getAllVendors();

    VendorDTO findVendorById(Long id);

    VendorDTO saveVendor(VendorDTO vendorDTO);

    VendorDTO saveVendorById(Long id, VendorDTO vendorDTO);

    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);

    void deleteVendorById(Long id);
}
