package com.udemy.sfgmvctest.controllers;

import com.udemy.sfgmvctest.api.v1.model.CustomerDTO;
import com.udemy.sfgmvctest.api.v1.model.CustomerListDTO;
import com.udemy.sfgmvctest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers() {
        return new ResponseEntity<CustomerListDTO>(new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.findCustomerById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.saveCustomer(customerDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerDTO> saveCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.saveCustomerById(id, customerDTO), HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.patchCustomer(id, customerDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
