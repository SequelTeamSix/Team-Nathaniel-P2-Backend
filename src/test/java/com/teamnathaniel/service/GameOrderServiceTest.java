package com.teamnathaniel.service;

import com.teamnathaniel.model.Game;
import com.teamnathaniel.model.GameOrder;
import com.teamnathaniel.model.Purchases;
import com.teamnathaniel.repository.GameOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class GameOrderServiceTest {
    private GameOrderService subject;

    private GameOrderRepository rEpOsItOrY;

    private List<GameOrder> allGameOrders;
    private GameOrder aGameOrder;

    private static boolean deleteCalled = false;

    @BeforeEach
    void sEtUp() {
        rEpOsItOrY = Mockito.mock(GameOrderRepository.class);
        subject = new GameOrderService(rEpOsItOrY);

        allGameOrders = new ArrayList<>();
        aGameOrder = new GameOrder();
        aGameOrder.setQuantity(125);
        aGameOrder.setGame(new Game());
        aGameOrder.setPurchases(new Purchases());
        allGameOrders.add(aGameOrder);
    }

    @Test
    void getAllGameOrders() {
        Mockito.when(rEpOsItOrY.findAll()).thenReturn(allGameOrders);
        assertEquals(subject.getAllGameOrders(), allGameOrders);
    }

    @Test
    void saveGameOrder() {
        Mockito.when(rEpOsItOrY.save(any(GameOrder.class))).then(invocationOnMock -> {
            return invocationOnMock.getArgument(0, GameOrder.class);
        });
        assertEquals(subject.saveGameOrder(aGameOrder), aGameOrder);
    }

    @Test
    void findGameOrderById() {
        Mockito.when(rEpOsItOrY.findByGameOrderId(0)).thenReturn(aGameOrder);
        assertEquals(subject.findGameOrderById(0), aGameOrder);
    }

    @Test
    void deleteGameOrder() {
        Mockito.doAnswer(invocationOnMock -> {
            deleteCalled = true;
            return null;
        }).when(rEpOsItOrY).deleteByGameOrderId(any(Integer.class));
        subject.deleteGameOrder(0);
        assertTrue(deleteCalled);
    }

    @Test
    void updateGameOrderUpdatesInsteadOfCreating() {
        Mockito.when(rEpOsItOrY.findByGameOrderId(any(Integer.class))).thenReturn(aGameOrder);
        Mockito.when(rEpOsItOrY.save(any(GameOrder.class))).then(invocationOnMock -> {
            return invocationOnMock.getArgument(0, GameOrder.class);
        });
        GameOrder aSecondGameOrder = new GameOrder();
        Game aGame = new Game();
        aGame.setTitle("New Title");
        aSecondGameOrder.setGame(aGame);
        aSecondGameOrder.setQuantity(511);
        Purchases aPurchase = new Purchases();
        aPurchase.setOrderDate("New Order Date");
        aSecondGameOrder.setPurchases(aPurchase);
        assertEquals(subject.updateGameOrder(0, aSecondGameOrder), aGameOrder);
        assertEquals(aGameOrder.getGame().getTitle(), "New Title");
        assertEquals(aGameOrder.getQuantity(), 511);
        assertEquals(aGameOrder.getPurchases().getOrderDate(), "New Order Date");
    }

    @Test
    void updateGameOrderNoPrev() {
        Mockito.when(rEpOsItOrY.findByGameOrderId(any(Integer.class))).thenReturn(null);
        Mockito.when(rEpOsItOrY.save(any(GameOrder.class))).then(invocationOnMock -> {
            return invocationOnMock.getArgument(0, GameOrder.class);
        });
        assertEquals(subject.updateGameOrder(0, aGameOrder), aGameOrder);
    }
}