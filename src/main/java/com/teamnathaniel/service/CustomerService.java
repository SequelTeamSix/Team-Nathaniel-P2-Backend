package com.teamnathaniel.service;

import com.teamnathaniel.model.Customer;
import com.teamnathaniel.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerService {
    CustomerRepository customerRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer){
        //Encoding user password using BCrypt
        String encodedPassword = this.passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);

        return customerRepository.save(customer);
    }

    public Customer findCustomerById(int customerId){
        return customerRepository.findById(customerId);
    }

    public void deleteCustomer(int customerId){
        customerRepository.deleteByCustomerId(customerId);
    }

    public Customer updateCustomer(int customerId, Customer customer){
        Customer oldCustomer = customerRepository.findById(customerId);
        if(oldCustomer != null){
            customer.setCustomerId(customer.getCustomerId());

            if(customer.getName() != null){
                customer.setName(customer.getName());
            }
            if(customer.getUsername() != null){
                customer.setUsername(customer.getUsername());
            }
            if(customer.getPassword() != null){
                String encodedPassword = this.passwordEncoder.encode(customer.getPassword());
                customer.setPassword(encodedPassword);
            }
        }
        return customerRepository.save(customer);
    }
}
