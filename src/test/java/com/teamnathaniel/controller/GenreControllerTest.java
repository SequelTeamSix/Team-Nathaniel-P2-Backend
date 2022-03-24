package com.teamnathaniel.controller;

import com.teamnathaniel.model.Game;
import com.teamnathaniel.model.Genre;
import com.teamnathaniel.service.GenreService;
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

@WebMvcTest(GenreController.class)
class GenreControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenreService sErViCe;

    private List<Genre> genres;
    private Genre aGenre;

    private static String genresJson = "[ { \"genre\": \"Horror\", \"game\": {} } ]";
    private static String genresFailer = "[ ]";
    private static String aGenreJson = "{ \"genre\": \"Horror\", \"game\": {} }";
    private static String aGenreFailer = "{ \"genre\": \"Shmorror\", \"game\": null }";
    private static boolean deleteCalled = false;

    @BeforeEach
    void sEtUp() {
        genres = new ArrayList<>();
        aGenre = new Genre();
        aGenre.setGenre("Horror");
        aGenre.setGame(new Game());
        genres.add(aGenre);
    }

    @Test
    void getAllGenres() throws Exception {
        Mockito.when(sErViCe.getAllGenres()).thenReturn(genres);
        this.mockMvc.perform(get("/getAllGenres"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(genresJson));
    }

    @Test
    void createGenre() throws Exception {
        Mockito.when(sErViCe.saveGenre(any(Genre.class))).thenReturn(aGenre);
        this.mockMvc.perform(post("/saveGenre")
                .contentType(MediaType.APPLICATION_JSON)
                .content(aGenreJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(aGenreJson));
    }

    @Test
    void findGenreById() throws Exception {
        Mockito.when(sErViCe.findGenreById(any(Integer.class))).thenReturn(aGenre);
        this.mockMvc.perform(get("/findGenre/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(aGenreJson));
    }

    @Test
    void deleteGenre() throws Exception {
        Mockito.doAnswer(invocationOnMock -> {
            deleteCalled = true;
            return null;
        }).when(sErViCe).deleteGenre(any(Integer.class));
        this.mockMvc.perform(delete("/deleteGenre/0"))
                .andDo(print())
                .andExpect(status().isOk());
        assertTrue(deleteCalled);
    }

    @Test
    void updateGenre() throws Exception {
        Mockito.when(sErViCe.updateGenre(any(Integer.class), any(Genre.class))).thenReturn(aGenre);
        this.mockMvc.perform(put("/updateGenre/0")
                .contentType(MediaType.APPLICATION_JSON)
                .content(aGenreJson))
                .andDo(print())
                .andExpect(content().json(aGenreJson));
    }
}