package com.teamnathaniel.controller;

import com.teamnathaniel.model.Game;
import com.teamnathaniel.model.GameOrder;
import com.teamnathaniel.model.Purchases;
import com.teamnathaniel.service.GameOrderService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameOrderController.class)
class GameOrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameOrderService sErViCe;

    private List<GameOrder> gameOrders;
    private GameOrder aGameOrder;

    // trying out some new stuff
    private static String gameOrdersjson = "[ { \"quantity\": 11, \"game\": {}, \"purchases\": {}} ]";
    private static String gameOrdersFailer = "[]";
    private static String aGameOrderjson = "{ \"quantity\": 11, \"game\": {}, \"purchases\": {}}";
    private static String aGameOrderFailer = "{ \"quantity\": 11, \"game\": { \"title\": \"Failer\" }, \"purchases\": {}}";
    private static boolean deleteCalled = false;

    @BeforeEach
    void sEtUp() {
        gameOrders = new ArrayList<>();
        aGameOrder = new GameOrder();
        aGameOrder.setGame(new Game());
        aGameOrder.setQuantity(11);
        aGameOrder.setPurchases(new Purchases());
        gameOrders.add(aGameOrder);
    }

    @Test
    void getAllGameOrders() throws Exception {
        Mockito.when(sErViCe.getAllGameOrders()).thenReturn(gameOrders);
        this.mockMvc.perform(get("/getAllGameOrders"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(gameOrdersjson));
    }

    @Test
    void createGameOrder() throws Exception {
        Mockito.when(sErViCe.saveGameOrder(any(GameOrder.class))).thenReturn(aGameOrder);
        this.mockMvc.perform((post("/saveGameOrder")
                .contentType(MediaType.APPLICATION_JSON)
                .content(aGameOrderjson)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(aGameOrderjson));
    }

    @Test
    void findGameOrderById() throws Exception {
        Mockito.when(sErViCe.findGameOrderById(any(Integer.class))).thenReturn(aGameOrder);
        this.mockMvc.perform(get("/findGameOrder/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(aGameOrderjson));
    }

    @Test
    void deleteGameOrder() throws Exception {
        Mockito.doAnswer(invocationOnMock -> {
            deleteCalled = true;
            return null;
        }).when(sErViCe).deleteGameOrder(any(Integer.class));
        this.mockMvc.perform(delete("/deleteGameOrder/0"))
                .andDo(print())
                .andExpect(status().isOk());
        assertTrue(deleteCalled);
    }

    @Test
    void updateGameOrder() throws Exception {
        Mockito.when(sErViCe.updateGameOrder(any(Integer.class), any(GameOrder.class))).thenReturn(aGameOrder);
        this.mockMvc.perform(put("/updateGameOrder/0")
                .contentType(MediaType.APPLICATION_JSON)
                .content(aGameOrderjson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(aGameOrderjson));
    }
}