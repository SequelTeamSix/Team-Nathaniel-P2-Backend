package com.teamnathaniel.controller;

import com.teamnathaniel.model.Character;
import com.teamnathaniel.model.Console;
import com.teamnathaniel.service.ConsoleService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConsoleController.class)
class ConsoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsoleService sErViCe;

    private List<Console> allConsoles;
    private Console xbox;

    private static boolean deleteCalled = false;

    @BeforeEach
    void sEtUp() {
        allConsoles = new ArrayList<>();
        Console playStation = new Console();
        playStation.setConsoleName("PlayStation");
        playStation.setReleaseDate("???");
        allConsoles.add(playStation);

        xbox = new Console();
        xbox.setReleaseDate("??!!");
        xbox.setConsoleName("Xbox");
    }

    @Test
    void getAllConsoles() throws Exception {
        Mockito.when(sErViCe.getAllConsoles()).thenReturn(allConsoles);
        this.mockMvc.perform(get("/getAllConsoles"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"consoleId\" : 0, \"consoleName\" : \"PlayStation\", \"releaseDate\" : \"???\" }]"));
    }

    @Test
    void createConsole() throws Exception {
        Mockito.when(sErViCe.saveConsole(any(Console.class))).thenReturn(xbox);
        this.mockMvc.perform(post("/saveConsole")
                        .content("{\"consoleName\":\"Xbox\", \"releaseDate\" : \"??!!\" }")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"consoleName\":\"Xbox\", \"releaseDate\" : \"??!!\" }"));

    }

    @Test
    void findConsoleById() throws Exception {
        Mockito.when(sErViCe.findConsoleById(any(Integer.class))).thenReturn(xbox);
        this.mockMvc.perform(get("/findConsole/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"consoleName\":\"Xbox\", \"releaseDate\" : \"??!!\" }"));
    }

    @Test
    void findConsoleByName() throws Exception {
        Mockito.when(sErViCe.findConsoleByName(any(String.class))).thenReturn(xbox);
        this.mockMvc.perform(get("/consoleName/Xbox"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"consoleName\":\"Xbox\", \"releaseDate\" : \"??!!\" }"));
    }

    @Test
    void deleteConsole() throws Exception {
        Mockito.doAnswer(invocationOnMock -> {
            deleteCalled = true;
            return null;
        }).when(sErViCe).deleteConsole(any(Integer.class));
        this.mockMvc.perform(delete("/deleteConsole/0"))
                .andDo(print())
                .andExpect(status().isOk()); // the json parser doesn't want to parse the literal value true, so I can't test the return value of this endpoint
        assertTrue(deleteCalled);
    }

    @Test
    void updateConsole() throws Exception {
        Mockito.when(sErViCe.updateConsole(any(Integer.class), any(Console.class))).thenReturn(xbox);
        this.mockMvc.perform(put("/updateConsole/0")
                        .content("{\"consoleName\":\"Xbox\", \"releaseDate\" : \"??!!\" }")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"consoleName\":\"Xbox\", \"releaseDate\" : \"??!!\" }"));
    }
}