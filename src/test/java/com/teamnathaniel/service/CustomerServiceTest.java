package com.teamnathaniel.service;

import com.teamnathaniel.model.Customer;
import com.teamnathaniel.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class CustomerServiceTest {
    private CustomerService subject;

    private CustomerRepository rEpOsItOrY;

    private List<Customer> allCustomers;

    private Customer customer;

    private static boolean deleteCalled = false;

    @BeforeEach
    void setUp() {
        rEpOsItOrY = Mockito.mock(CustomerRepository.class);
        subject = new CustomerService(rEpOsItOrY);
        subject.em = Mockito.mock(EntityManager.class);

        allCustomers = new ArrayList<>();
        customer = new Customer();
        customer.setName("A customer");
        customer.setPassword("A password");
        customer.setUsername("customermaid");
        allCustomers.add(customer);
    }

    @Test
    void getAllCustomers() {
        Mockito.when(rEpOsItOrY.findAll()).thenReturn(allCustomers);
        assertEquals(subject.getAllCustomers(), allCustomers);
    }

    @Test
    void saveCustomer() {
        Mockito.when(rEpOsItOrY.save(any(Customer.class))).thenReturn(customer);
        assertEquals(subject.saveCustomer(customer), customer);
    }

    @Test
    void saveCustomerChangesPasswordToHash() {
        String oldPassword = customer.getPassword();
        Mockito.when(rEpOsItOrY.save(any(Customer.class))).then(invocationOnMock -> {
            return invocationOnMock.getArgument(0, Customer.class);
        });
        assertNotEquals(subject.saveCustomer(customer).getPassword(), oldPassword);
    }

    @Test
    void hashIsRepeatable() {
        String oldPassword = customer.getPassword();
        Mockito.when(rEpOsItOrY.save(any(Customer.class))).then(invocationOnMock -> {
            return invocationOnMock.getArgument(0, Customer.class);
        });
        String firstHash = subject.saveCustomer(customer).getPassword();
        customer.setPassword(oldPassword);
        String secondHash = subject.saveCustomer(customer).getPassword();
        assertEquals(firstHash, secondHash);
    }

    @Test
    void hashErrorsOnNullPassword() {
        Mockito.when(rEpOsItOrY.save(any(Customer.class))).then(invocationOnMock -> {
            return invocationOnMock.getArgument(0, Customer.class);
        });
        customer.setPassword(null);
        assertEquals(subject.saveCustomer(customer).getPassword(), null);
    }

    @Test
    void findCustomerById() {
        Mockito.when(rEpOsItOrY.findById(0)).thenReturn(customer);
        assertEquals(subject.findCustomerById(0), customer);
    }

    @Test
    void deleteCustomer() {
        Mockito.doAnswer(invocationOnMock -> {
            deleteCalled = true;
            return null;
        }).when(rEpOsItOrY).deleteByCustomerId(any(Integer.class));
        subject.deleteCustomer(0);
        assertTrue(deleteCalled);
    }

    @Test
    void customerLogin() {
        Mockito.when(rEpOsItOrY.getCustomerCredentials(any(String.class), any(String.class))).then(invocationOnMock -> {
            String hashedPass = invocationOnMock.getArgument(1, String.class);
            customer.setPassword(hashedPass);
            return customer;
        });
        String oldPass = customer.getPassword();
        assertFalse(subject.customerLogin(customer).getPassword() == oldPass);
    }

    @Test
    void updateCustomer() { // TODO having trouble mocking the EntityManager, can't really mock that it updates correctly
        Mockito.when(rEpOsItOrY.findById(0)).thenReturn(customer);
        Mockito.when(subject.em.merge(any())).thenReturn(customer);
        subject.updateCustomer(0, customer);
    }
}