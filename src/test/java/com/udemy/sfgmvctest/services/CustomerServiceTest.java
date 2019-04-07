package com.udemy.sfgmvctest.services;

import com.udemy.sfgmvctest.api.v1.mapper.CustomerMapper;
import com.udemy.sfgmvctest.api.v1.model.CustomerDTO;
import com.udemy.sfgmvctest.domain.Customer;
import com.udemy.sfgmvctest.repositories.CustomerRepository;
import com.udemy.sfgmvctest.services.impl.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    private static final String FIRST_NAME = "Jimmy";
    private static final String LAST_NAME = "Jones";
    private static final long ID = 1L;
    private static final long NOT_FOUND_ID = 2L;

    private CustomerService customerService;
    @Mock
    private CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void testGetAllCustomers() {
        when(customerRepository.findAll()).thenReturn(Arrays.asList(new Customer(), new Customer()));

        List<CustomerDTO> customers = customerService.getAllCustomers();

        assertNotNull(customers);
        assertEquals(2, customers.size());
    }

    @Test
    public void testFindCustomerById() {
        //given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        when(customerRepository.findById(ID)).thenReturn(Optional.of(customer));
        when(customerRepository.findById(NOT_FOUND_ID)).thenReturn(Optional.empty());

        CustomerDTO customerDTO = customerService.findCustomerById(ID);

        assertEquals(customerDTO.getFirstName(), customer.getFirstName());
        assertEquals(customerDTO.getLastName(), customer.getLastName());

        assertNull(customerService.findCustomerById(NOT_FOUND_ID));
    }

    @Test
    public void deleteCustomerById() throws Exception {

        Long id = 1L;

        customerRepository.deleteById(id);

        verify(customerRepository, times(1)).deleteById(anyLong());
    }
}