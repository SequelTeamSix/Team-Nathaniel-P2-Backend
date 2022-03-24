package com.teamnathaniel.repository;

import com.teamnathaniel.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("From Customer")
    List<Customer> findAll();

    @Query("From Customer where customerId = :customerId")
    Customer findById(int customerId);

    Customer save(Customer customer);

    void deleteByCustomerId(int customerId);

    @Query("from Customer where username = :username and password = :password")
    Customer getCustomerCredentials(String username, String password);

    Customer findByUsername(String username);
}
