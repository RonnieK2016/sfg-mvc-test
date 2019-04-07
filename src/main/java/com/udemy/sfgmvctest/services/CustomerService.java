package com.udemy.sfgmvctest.services;

import com.udemy.sfgmvctest.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO findCustomerById(Long id);

    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    CustomerDTO saveCustomerById(Long id, CustomerDTO customerDTO);

    CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomerById(Long id);
}
