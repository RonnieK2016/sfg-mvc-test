package com.udemy.sfgmvctest.bootstrap;

import com.udemy.sfgmvctest.domain.Category;
import com.udemy.sfgmvctest.domain.Customer;
import com.udemy.sfgmvctest.domain.Vendor;
import com.udemy.sfgmvctest.repositories.CategoryRepository;
import com.udemy.sfgmvctest.repositories.CustomerRepository;
import com.udemy.sfgmvctest.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private CategoryRepository categoryRepository;

    private CustomerRepository customerRepository;

    private VendorRepository vendorRepository;

    public DataLoader(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(categoryRepository.count() == 0) {
            loadCategoryData();
        }

        if(customerRepository.count() == 0) {
            loadCustomerData();
        }

        if(vendorRepository.count() == 0) {
            loadVendorData();
        }
    }

    private void loadVendorData() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Vendor 1");
        vendorRepository.save(vendor1);

        Vendor vendor2 = new Vendor();
        vendor2.setName("Vendor 2");
        vendorRepository.save(vendor2);
    }

    private void loadCustomerData() {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("Michale");
        customer1.setLastName("Weston");
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Sam");
        customer2.setLastName("Axe");

        customerRepository.save(customer2);

        System.out.println("Customers Loaded: " + customerRepository.count());
    }

    private void loadCategoryData() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Data Loaded: " + categoryRepository.count());
    }
}
