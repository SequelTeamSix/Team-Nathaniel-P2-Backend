package com.teamnathaniel.service;

import com.teamnathaniel.model.*;
import com.teamnathaniel.repository.PurchasesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class PurchasesServiceTest {
    private PurchasesService subject;

    private PurchasesRepository rEpOsItOrY;

    private List<Purchases> allPurchases;
    private Purchases aPurchase;

    private static boolean deleteCalled = false;

    @BeforeEach
    void setUp() {
        rEpOsItOrY = Mockito.mock(PurchasesRepository.class);
        subject = new PurchasesService(rEpOsItOrY);

        allPurchases = new ArrayList<>();
        aPurchase = new Purchases();
        aPurchase.setOrderDate("Now");
        aPurchase.setCustomer(new Customer());
        aPurchase.setGameOrders(new ArrayList<>());
        allPurchases.add(aPurchase);
    }

    @Test
    void getAllPurchases() {
        Mockito.when(rEpOsItOrY.findAll()).thenReturn(allPurchases);
        assertEquals(subject.getAllPurchases(), allPurchases);
    }

    @Test
    void savePurchase() {
        Mockito.when(rEpOsItOrY.save(any(Purchases.class))).then(invocationOnMock -> {
            return invocationOnMock.getArgument(0, Purchases.class);
        });
        assertEquals(subject.savePurchase(aPurchase), aPurchase);
    }

    @Test
    void findPurchaseById() {
        Mockito.when(rEpOsItOrY.findById(0)).thenReturn(aPurchase);
        assertEquals(subject.findPurchaseById(0), aPurchase);
    }

    @Test
    void deletePurchase() {
        Mockito.doAnswer(invocationOnMock -> {
            deleteCalled = true;
            return null;
        }).when(rEpOsItOrY).deleteByPurchasesId(any(Integer.class));
        subject.deletePurchase(0);
        assertTrue(deleteCalled);
    }

    @Test
    void updatePurchaseUpdatesInsteadOfCreating() {
        Mockito.when(rEpOsItOrY.findById(0)).thenReturn(aPurchase);
        Mockito.when(rEpOsItOrY.save(any(Purchases.class))).then(invocationOnMock -> {
            return invocationOnMock.getArgument(0, Purchases.class);
        });
        Purchases aSecondPurchase = new Purchases();
        aSecondPurchase.setOrderDate("Not Now");
        Customer customer = new Customer();
        customer.setName("New Customer");
        aSecondPurchase.setCustomer(customer);
        GameOrder gameOrder = new GameOrder();
        gameOrder.setQuantity(512);
        List<GameOrder> gameOrders = new ArrayList<>();
        gameOrders.add(gameOrder);
        aSecondPurchase.setGameOrders(gameOrders);
        assertEquals(subject.updatePurchase(0, aSecondPurchase), aPurchase);
        assertEquals(aPurchase.getOrderDate(), "Not Now");
        assertEquals(aPurchase.getCustomer().getName(), "New Customer");
        assertEquals(aPurchase.getGameOrders().get(0).getQuantity(), 512);
    }

    @Test
    void updatePurchasesNoPrev() {
        Mockito.when(rEpOsItOrY.findById(any(Integer.class))).thenReturn(null);
        Mockito.when(rEpOsItOrY.save(any(Purchases.class))).then(invocationOnMock -> {
            return invocationOnMock.getArgument(0, Purchases.class);
        });
        assertEquals(subject.updatePurchase(0, aPurchase), aPurchase);
    }
}