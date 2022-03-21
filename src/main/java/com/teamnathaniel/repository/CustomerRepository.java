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

    @Query("From Customer where customerId = :id")
    Customer findById(int id);

    Customer save(Customer customer);

    void deleteCustomerById(int customerId);
}
