package com.udemy.sfgmvctest.services.impl;

import com.udemy.sfgmvctest.api.v1.mapper.VendorMapper;
import com.udemy.sfgmvctest.api.v1.model.VendorDTO;
import com.udemy.sfgmvctest.domain.Vendor;
import com.udemy.sfgmvctest.exceptions.ResourceNotFoundException;
import com.udemy.sfgmvctest.repositories.VendorRepository;
import com.udemy.sfgmvctest.services.VendorService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.udemy.sfgmvctest.api.v1.constants.ApiConstants.VENDORS_BASE_URL;

@Service
public class VendorServiceImpl implements VendorService {

    private VendorRepository vendorRepository;
    private VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }


    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(VENDORS_BASE_URL + vendor.getId());
                    return vendorDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO findVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(VENDORS_BASE_URL + vendor.getId());
                    return vendorDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO saveVendor(VendorDTO vendorDTO) {

        Vendor savedVendor = vendorRepository.save(vendorMapper.vendorDtoTOVendor(vendorDTO));
        VendorDTO savedDto = vendorMapper.vendorToVendorDTO(savedVendor);
        savedDto.setVendorUrl(VENDORS_BASE_URL + savedVendor.getId());

        return savedDto;
    }

    @Override
    public VendorDTO saveVendorById(Long id, VendorDTO vendorDTO) {
        vendorDTO.setId(id);
        return saveVendor(vendorDTO);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id).map(
                vendor -> {
                    if(!StringUtils.isEmpty(vendorDTO.getName())) {
                        vendor.setName(vendorDTO.getName());
                    }
                    VendorDTO returnDto = vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
                    returnDto.setVendorUrl(VENDORS_BASE_URL + returnDto.getId());

                    return returnDto;
                }
        ).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }
}
