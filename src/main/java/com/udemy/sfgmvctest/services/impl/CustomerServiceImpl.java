package com.udemy.sfgmvctest.services.impl;

import com.udemy.sfgmvctest.api.v1.mapper.CustomerMapper;
import com.udemy.sfgmvctest.api.v1.model.CustomerDTO;
import com.udemy.sfgmvctest.domain.Customer;
import com.udemy.sfgmvctest.exceptions.ResourceNotFoundException;
import com.udemy.sfgmvctest.repositories.CustomerRepository;
import com.udemy.sfgmvctest.services.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customers/"+customer.getId());
                    return customerDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Customer savedCustomer = customerRepository.save(customerMapper.customerDtoToCustomer(customerDTO));
        CustomerDTO returnCustomerDto = customerMapper.customerToCustomerDTO(savedCustomer);

        returnCustomerDto.setCustomerUrl("/api/v1/customers/"+returnCustomerDto.getId());

        return  returnCustomerDto;
    }

    @Override
    public CustomerDTO saveCustomerById(Long id, CustomerDTO customerDTO) {
        customerDTO.setId(id);
        return saveCustomer(customerDTO);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id)
                .map(customer -> {
                    if(!StringUtils.isEmpty(customerDTO.getFirstName())) {
                        customer.setFirstName(customerDTO.getFirstName());
                    }
                    if(!StringUtils.isEmpty(customerDTO.getLastName())) {
                        customer.setLastName(customerDTO.getLastName());
                    }

                    CustomerDTO resultCustomerDTO = customerMapper.customerToCustomerDTO(customerRepository.save(customer));

                    resultCustomerDTO.setCustomerUrl("/api/v1/customers/" + resultCustomerDTO.getId());

                    return resultCustomerDTO;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
