package com.udemy.sfgmvctest.controllers;

import com.udemy.sfgmvctest.api.v1.model.CustomerDTO;
import com.udemy.sfgmvctest.api.v1.model.VendorDTO;
import com.udemy.sfgmvctest.api.v1.model.VendorListDTO;
import com.udemy.sfgmvctest.services.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vendors")
@Api("Vendor API")
public class VendorController {

    private VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Returns All Vendors")
    public VendorListDTO getAllVendors() {
        return new VendorListDTO(vendorService.getAllVendors());
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Returns Vendor By Id")
    public VendorDTO getVendorById(@PathVariable Long id) {
        return vendorService.findVendorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Creates Vendor")
    public VendorDTO createVendor(@RequestBody VendorDTO vendorDTO) {
        return vendorService.saveVendor(vendorDTO);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Updates Vendor")
    public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.saveVendorById(id, vendorDTO);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Updates Certain Vendor Parameters")
    public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return  vendorService.patchVendor(id, vendorDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Removes Vendor")
    public void deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendorById(id);
    }
}
