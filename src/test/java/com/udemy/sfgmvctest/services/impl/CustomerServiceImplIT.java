package com.udemy.sfgmvctest.services.impl;

import com.udemy.sfgmvctest.api.v1.mapper.CustomerMapper;
import com.udemy.sfgmvctest.api.v1.model.CustomerDTO;
import com.udemy.sfgmvctest.bootstrap.DataLoader;
import com.udemy.sfgmvctest.domain.Customer;
import com.udemy.sfgmvctest.repositories.CategoryRepository;
import com.udemy.sfgmvctest.repositories.CustomerRepository;
import com.udemy.sfgmvctest.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        DataLoader dataLoader = new DataLoader(categoryRepository, customerRepository);
        dataLoader.run();

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void patchCustomerUpdateFirstName() {
        String updatedName = "UpdatedName";

        Customer originalCustomer = getFirstCustomer();

        Long id = originalCustomer.getId();

        assertNotNull(originalCustomer);
        //save original first name
        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(updatedName);

        customerService.patchCustomer(id, customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(updatedName, updatedCustomer.getFirstName());
        assertThat(originalFirstName, not(equalTo(updatedCustomer.getFirstName())));
        assertThat(originalLastName, equalTo(updatedCustomer.getLastName()));
    }

    @Test
    public void patchCustomerUpdateLastName() {
        String updatedName = "UpdatedName";

        Customer originalCustomer = getFirstCustomer();

        Long id = originalCustomer.getId();

        assertNotNull(originalCustomer);

        //save original first/last name
        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getFirstName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastName(updatedName);

        customerService.patchCustomer(id, customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertNotNull(updatedCustomer);
        assertEquals(updatedName, updatedCustomer.getLastName());
        assertThat(originalFirstName, equalTo(updatedCustomer.getFirstName()));
        assertThat(originalLastName, not(equalTo(updatedCustomer.getLastName())));
    }

    private Customer getFirstCustomer(){
        return customerRepository.findAll().get(0);
    }
}