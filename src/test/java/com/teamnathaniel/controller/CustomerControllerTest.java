package com.teamnathaniel.controller;

import com.teamnathaniel.model.Customer;
import com.teamnathaniel.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService sErViCe;

    private List<Customer> allCustomers;
    private Customer steveBuscemi;

    private static boolean deleteCalled = false;

    @BeforeEach
    void sEtUp() {
        allCustomers = new ArrayList<>();
        steveBuscemi = new Customer();
        steveBuscemi.setPassword("password");
        steveBuscemi.setUsername("steve");
        steveBuscemi.setName("Steve Buscemi");
        allCustomers.add(steveBuscemi);
    }

    @Test
    void getAllCustomers() throws Exception {
        Mockito.when(sErViCe.getAllCustomers()).thenReturn(allCustomers);
        this.mockMvc.perform(get("/getAllCustomers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\": \"Steve Buscemi\", \"username\": \"steve\", \"password\": \"password\"}]"));
    }

    @Test
    void createCustomer() throws Exception {
        Mockito.when(sErViCe.saveCustomer(any(Customer.class))).thenReturn(steveBuscemi);
        this.mockMvc.perform(post("/saveCustomer")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Steve Buscemi\", \"username\": \"steve\", \"password\": \"password\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\": \"Steve Buscemi\", \"username\": \"steve\", \"password\": \"password\"}"));
    }

    @Test
    void findCustomerById() throws Exception {
        Mockito.when(sErViCe.findCustomerById(any(Integer.class))).thenReturn(steveBuscemi);
        this.mockMvc.perform(get("/findCustomer/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\": \"Steve Buscemi\", \"username\": \"steve\", \"password\": \"password\"}"));
    }

    @Test
    void deleteCustomer() throws Exception {
        Mockito.doAnswer(invocationOnMock -> {
            deleteCalled = true;
            return null;
        }).when(sErViCe).deleteCustomer(any(Integer.class));
        this.mockMvc.perform((delete("/deleteCustomer/0")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateCustomer() throws Exception {
        Mockito.when(sErViCe.updateCustomer(any(Integer.class), any(Customer.class))).thenReturn(steveBuscemi);
        this.mockMvc.perform(put("/updateCustomer/0")
                .contentType((MediaType.APPLICATION_JSON))
                .content("{\"name\": \"Sleeve Bushcemy\", \"username\": \"stove\", \"password\": \"possward\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\": \"Steve Buscemi\", \"username\": \"steve\", \"password\": \"password\"}"));
    }

    @Test
    void customerLogin() throws Exception {
        Mockito.when(sErViCe.customerLogin(any(Customer.class))).thenReturn(steveBuscemi);
        this.mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Steve Buscemi\", \"username\": \"steve\", \"password\": \"password\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\": \"Steve Buscemi\", \"username\": \"steve\", \"password\": \"password\"}"));
    }
}