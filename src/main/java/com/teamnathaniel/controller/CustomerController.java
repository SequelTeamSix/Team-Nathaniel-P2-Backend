package com.teamnathaniel.controller;

import com.teamnathaniel.model.Customer;
import com.teamnathaniel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("getAllCustomers")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PostMapping("saveCustomer")
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @GetMapping("findCustomer/{id}")
    public Customer findCustomerById(@PathVariable int id){
        return customerService.findCustomerById(id);
    }

    @DeleteMapping("deleteCustomer/{customerId}")
    public boolean deleteCustomer(@PathVariable int customerId){
        customerService.deleteCustomer(customerId);
        System.out.println("Customer with id " + customerId + " was deleted. ");
        return true;
    }

    @PutMapping("updateCustomer/{customerId}")
    public Customer updateCustomer(@PathVariable int customerId, @RequestBody Customer customer){
        return customerService.updateCustomer(customerId, customer);
    }
}
