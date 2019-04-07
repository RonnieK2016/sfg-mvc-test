package com.udemy.sfgmvctest.controllers;

import com.udemy.sfgmvctest.api.v1.model.CustomerDTO;
import com.udemy.sfgmvctest.api.v1.model.CustomerListDTO;
import com.udemy.sfgmvctest.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@Api("Customers API")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Returns All Customers")
    public CustomerListDTO getAllCustomers() {
        return new CustomerListDTO(customerService.getAllCustomers());
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Returns Customer By Id")
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Creates New Customer")
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.saveCustomer(customerDTO);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Updates Customer")
    public CustomerDTO saveCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.saveCustomerById(id, customerDTO);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Updates Certain Customer Parameters")
    public CustomerDTO patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.patchCustomer(id, customerDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Removes Customer")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
    }
}
