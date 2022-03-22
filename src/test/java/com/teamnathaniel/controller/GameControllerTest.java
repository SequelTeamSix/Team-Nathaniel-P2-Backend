package com.teamnathaniel.controller;

import com.teamnathaniel.model.Game;
import com.teamnathaniel.model.Purchases;
import com.teamnathaniel.service.GameService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(GameController.class)
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService sErViCe;

    private List<Game> allGames;

    private Game halo;

    private static boolean deleteCalled = false;
    @BeforeEach
    void sEtUp() {
        allGames = new ArrayList<>();
        Game tyTheTasmanianTiger = new Game();
        tyTheTasmanianTiger.setGamePrice(10);
        tyTheTasmanianTiger.setTitle("Ty the Tasmanian Tiger");
        tyTheTasmanianTiger.setNumPlayers(1);
        tyTheTasmanianTiger.setReleaseDate("2000s ish?");
        tyTheTasmanianTiger.setOnline(false);
        allGames.add(tyTheTasmanianTiger);

        halo = new Game();
        halo.setGamePrice(50);
        halo.setTitle("Halo");
        halo.setNumPlayers(4);
        halo.setOnline(true);
        halo.setReleaseDate("also 2000s ish?");
    }

    @Test
    void getAllGames() throws Exception {
        when(sErViCe.getAllGames()).thenReturn(allGames);
        this.mockMvc.perform(get("/getAllGames"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"gameId\":0,\"title\":\"Ty the Tasmanian Tiger\",\"releaseDate\":\"2000s ish?\",\"gamePrice\":10,\"numPlayers\":1,\"online\":false}]"));
    }

    @Test
    void createGame() throws Exception {
        when(sErViCe.saveGame(any(Game.class))).then(invocationOnMock -> {
            return invocationOnMock.getArgument(0, Game.class);
        });
        this.mockMvc.perform(post("/saveGame")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Halo 2\",\"releaseDate\":\"also also 2000s ish?\",\"gamePrice\":50,\"numPlayers\":4,\"online\":true}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"title\":\"Halo 2\",\"releaseDate\":\"also also 2000s ish?\",\"gamePrice\":50,\"numPlayers\":4,\"online\":true}"));
    }

    @Test
    void findGameById() throws Exception {
        when(sErViCe.findGameById(any(Integer.class))).thenReturn(halo);
        this.mockMvc.perform(get("/findGame/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"title\":\"Halo\",\"releaseDate\":\"also 2000s ish?\",\"gamePrice\":50,\"numPlayers\":4,\"online\":true}"));
    }

    @Test
    void findGameByName() throws Exception {
        when(sErViCe.findGameByName(any(String.class))).thenReturn(halo);
        this.mockMvc.perform(get("/gameName/Halo"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"title\":\"Halo\",\"releaseDate\":\"also 2000s ish?\",\"gamePrice\":50,\"numPlayers\":4,\"online\":true}"));
    }

    @Test
    void deleteGame() throws Exception {
        Mockito.doAnswer(invocationOnMock -> {
            deleteCalled = true;
            return null;
        }).when(sErViCe).deleteGame(any(Integer.class));
        this.mockMvc.perform(delete("/deleteGame/0"))
                .andDo(print())
                .andExpect(status().isOk());
        assertTrue(deleteCalled);
    }

    @Test
    void updateGame() throws Exception {
        Mockito.when(sErViCe.updateGame(any(Integer.class), any(Game.class))).thenReturn(halo);
        this.mockMvc.perform(put("/updateGame/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Halo\",\"releaseDate\":\"also 2000s ish?\",\"gamePrice\":50,\"numPlayers\":4,\"online\":true}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"title\":\"Halo\",\"releaseDate\":\"also 2000s ish?\",\"gamePrice\":50,\"numPlayers\":4,\"online\":true}"));
    }
}