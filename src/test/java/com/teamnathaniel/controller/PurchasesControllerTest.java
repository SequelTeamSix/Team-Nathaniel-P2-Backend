package com.teamnathaniel.controller;

import com.teamnathaniel.model.Console;
import com.teamnathaniel.model.Customer;
import com.teamnathaniel.model.GameOrder;
import com.teamnathaniel.model.Purchases;
import com.teamnathaniel.service.ConsoleService;
import com.teamnathaniel.service.PurchasesService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PurchasesController.class)
class PurchasesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PurchasesService sErViCe;

    private List<Purchases> allPurchases;
    private Purchases purchases;

    private static boolean deleteCalled = false;

    @BeforeEach
    void sEtUp() {
        Customer customer = new Customer();
        customer.setName("Steve Buscemi");
        customer.setUsername("BushyBusc");
        customer.setPassword("password");
        List<GameOrder> gameOrders = new ArrayList<>();

        allPurchases = new ArrayList<>();
        Purchases purchases1 = new Purchases();
        purchases1.setOrderDate("Today");
        purchases1.setCustomer(customer); // TODO figure out how to add a customer to a purchase object cleanly
        purchases1.setGameOrders(gameOrders);
        allPurchases.add(purchases1);

        purchases = new Purchases();
        purchases.setCustomer(customer);
        purchases.setOrderDate("Tomorrow");
        purchases.setGameOrders(gameOrders);
    }

    @Test
    void getAllPurchase() throws Exception {
        Mockito.when(sErViCe.getAllPurchases()).thenReturn(allPurchases);
        this.mockMvc.perform(get("/getAllPurchases"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"orderDate\":\"Today\",\"customer\":{\"customerId\":0,\"username\":\"BushyBusc\",\"password\":\"password\",\"name\":\"Steve Buscemi\"},\"gameOrders\":[]}]"));
    }

    @Test
    void createPurchase() throws Exception {
        Mockito.when(sErViCe.savePurchase(any(Purchases.class))).thenReturn(purchases);
        this.mockMvc.perform(post("/savePurchase")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"orderDate\":\"Tomorrow\",\"customer\":{\"customerId\":0,\"username\":\"BushyBusc\",\"password\":\"password\",\"name\":\"Steve Buscemi\"},\"gameOrders\":[]}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"orderDate\":\"Tomorrow\",\"customer\":{\"customerId\":0,\"username\":\"BushyBusc\",\"password\":\"password\",\"name\":\"Steve Buscemi\"},\"gameOrders\":[]}"));
    }

    @Test
    void findPurchaseById() throws Exception {
        Mockito.when(sErViCe.findPurchaseById(any(Integer.class))).thenReturn(purchases);
        this.mockMvc.perform(get("/findPurchase/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"orderDate\":\"Tomorrow\",\"customer\":{\"customerId\":0,\"username\":\"BushyBusc\",\"password\":\"password\",\"name\":\"Steve Buscemi\"},\"gameOrders\":[]}"));
    }

    @Test
    void deletePurchase() throws Exception {
        Mockito.doAnswer(invocationOnMock -> {
            deleteCalled = true;
            return null;
        }).when(sErViCe).deletePurchase(any(Integer.class));
        this.mockMvc.perform(delete("/deletePurchase/0"))
                .andDo(print())
                .andExpect(status().isOk());
        assertTrue(deleteCalled);
    }

    @Test
    void updatePurchase() throws Exception {
        Mockito.when(sErViCe.updatePurchase(any(Integer.class), any(Purchases.class))).thenReturn(purchases);
        this.mockMvc.perform(put("/updatePurchase/0")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"orderDate\":\"Tomorrow\",\"customer\":{\"customerId\":0,\"username\":\"BushyBusc\",\"password\":\"password\",\"name\":\"Steve Buscemi\"},\"gameOrders\":[]}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"orderDate\":\"Tomorrow\",\"customer\":{\"customerId\":0,\"username\":\"BushyBusc\",\"password\":\"password\",\"name\":\"Steve Buscemi\"},\"gameOrders\":[]}"));
    }
}