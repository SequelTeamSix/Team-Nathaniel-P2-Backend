package com.teamnathaniel.controller;

import com.teamnathaniel.model.Series;
import com.teamnathaniel.service.SeriesService;
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
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SeriesController.class)
class SeriesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeriesService sErViCe;

    private List<Series> allSeries;
    private Series darkSouls;

    @BeforeEach
    public void sEtUp() {
        allSeries = new ArrayList<>();
        Series granTurismo = new Series();
        granTurismo.setName("Gran Turismo");
        granTurismo.setDescription("A driving game series that combines realism and fun");
        granTurismo.setCharacter(new ArrayList<>());
        allSeries.add(granTurismo);

        darkSouls = new Series();
        darkSouls.setName("Dark Souls");
        darkSouls.setDescription("An action rpg series famous for being difficult");
        darkSouls.setCharacter(new ArrayList<>());
    }

    @Test
    void getAllSeries() throws Exception {
        when(sErViCe.getAllSeries()).thenReturn(allSeries);
        this.mockMvc.perform(get("/getAllSeries"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"Gran Turismo\",\"description\":\"A driving game series that combines realism and fun\", \"character\": []}]"));
    }

    @Test
    void createSeries() throws Exception {
        when(sErViCe.saveSeries(any(Series.class))).thenReturn(darkSouls);
        this.mockMvc.perform(post("/saveSeries")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Dark Souls\",\"description\":\"An action rpg series famous for being difficult\",\"character\":[]}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\":\"Dark Souls\",\"description\":\"An action rpg series famous for being difficult\",\"character\":[]}"));
    }

    @Test
    void findSeriesById() throws Exception {
        when(sErViCe.findSeriesById(0)).thenReturn(darkSouls);
        this.mockMvc.perform(get("/findSeries/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\":\"Dark Souls\",\"description\":\"An action rpg series famous for being difficult\",\"character\":[]}"));
    }

    @Test
    void findSeriesByName() throws Exception{
        when(sErViCe.findSeriesByName("Dark Souls")).thenReturn(darkSouls);
        this.mockMvc.perform(get("/SeriesName/Dark Souls"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\":\"Dark Souls\",\"description\":\"An action rpg series famous for being difficult\",\"character\":[]}"));
    }

    @Test
    void deleteSeries() throws Exception {
        Mockito.doNothing().when(sErViCe).deleteSeries(any(Integer.class));
        this.mockMvc.perform(delete("/deleteSeries/0"))
                .andDo(print())
                .andExpect(status().isOk()); // the json parser doesn't want to parse the literal value true, so I can't test the return value of this endpoint
    }

    @Test
    void updateSeries() throws Exception {
        when(sErViCe.updateSeries(any(Integer.class), any(Series.class))).thenReturn(darkSouls);
        this.mockMvc.perform(put("/updateSeries/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Dark Souls\",\"description\":\"An action rpg series famous for being difficult\",\"character\":[]}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\":\"Dark Souls\",\"description\":\"An action rpg series famous for being difficult\",\"character\":[]}"));

    }
}