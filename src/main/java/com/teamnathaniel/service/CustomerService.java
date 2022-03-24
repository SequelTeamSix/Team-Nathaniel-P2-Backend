package com.teamnathaniel.service;

import com.teamnathaniel.model.Customer;
import com.teamnathaniel.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;



@Component
public class CustomerService {
    @PersistenceContext
    EntityManager em;

    CustomerRepository customerRepository;


    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer){
        Customer customer1 =  customerRepository.findByUsername(customer.getUsername());
        if(customer1 !=  null)
        {
            return customer1;
        }
        String hashPassword = hashPassword(customer.getPassword());
        customer.setPassword(hashPassword);
        return customerRepository.save(customer);
    }

    public Customer findCustomerById(int customerId){
        return customerRepository.findById(customerId);
    }

    public void deleteCustomer(int customerId){
        customerRepository.deleteByCustomerId(customerId);
    }

    public Customer customerLogin(Customer customer){
        String hashPassword = hashPassword(customer.getPassword());
        return customerRepository.getCustomerCredentials(customer.getUsername(),
                hashPassword);
    }

    @Transactional
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
                String hashPassword = hashPassword(customer.getPassword());
                customer.setPassword(hashPassword);

            }
        }
        return em.merge(customer);
    }
    private String hashPassword(String plainPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(plainPassword.getBytes(StandardCharsets.UTF_8));
            byte byteData[] = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int x = 0; x < byteData.length; x++) {
                sb.append(Integer.toString((byteData[x] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch(Exception e) {
            System.out.println("Error");
            return null;
        }
    }
}
