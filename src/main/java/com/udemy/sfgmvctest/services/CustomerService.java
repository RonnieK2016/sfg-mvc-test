package com.udemy.sfgmvctest.services;

import com.udemy.sfgmvctest.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO findCustomerById(Long id);
}
