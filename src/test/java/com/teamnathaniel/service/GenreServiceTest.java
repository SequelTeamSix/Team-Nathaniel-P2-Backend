package com.teamnathaniel.service;

import com.teamnathaniel.model.Game;
import com.teamnathaniel.model.Genre;
import com.teamnathaniel.repository.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class GenreServiceTest {
    private GenreService subject;

    private GenreRepository rEpOsItOrY;

    private List<Genre> allGenres;
    private Genre aGenre;

    private static boolean deleteCalled = false;

    @BeforeEach
    void setUp() {
        rEpOsItOrY = Mockito.mock(GenreRepository.class);
        subject = new GenreService(rEpOsItOrY);

        allGenres = new ArrayList<>();
        aGenre = new Genre();
        aGenre.setGenre("Horror");
        Game game = new Game();
        game.setTitle("Silent Hill");
        aGenre.setGame(game);
        allGenres.add(aGenre);
    }

    @Test
    void getAllGenres() {
        Mockito.when(rEpOsItOrY.findAll()).thenReturn(allGenres);
        assertEquals(subject.getAllGenres(), allGenres);
    }

    @Test
    void saveGenre() {
        Mockito.when(rEpOsItOrY.save(any(Genre.class))).thenReturn(aGenre);
        assertEquals(subject.saveGenre(aGenre), aGenre);
    }

    @Test
    void findGenreById() {
        Mockito.when(rEpOsItOrY.findByGenreId(any(Integer.class))).thenReturn(aGenre);
        assertEquals(subject.findGenreById(0), aGenre);
    }

    @Test
    void deleteGenre() {
        Mockito.doAnswer(invocationOnMock -> {
            deleteCalled = true;
            return null;
        }).when(rEpOsItOrY).deleteByGenreId(any(Integer.class));
        subject.deleteGenre(0);
        assertTrue(deleteCalled);
    }

    @Test
    void updateGenreUpdatesInsteadOfCreating() {
        Mockito.when(rEpOsItOrY.findByGenreId(any(Integer.class))).thenReturn(aGenre);
        Mockito.when(rEpOsItOrY.save(any(Genre.class))).then(invocationOnMock -> {
            return invocationOnMock.getArgument(0, Genre.class);
        });
        Genre aSecondGenre = new Genre();
        aSecondGenre.setGenre("Schmorror");
        Game game = new Game();
        game.setTitle("Bobby Hill");
        aSecondGenre.setGame(game);
        assertEquals(subject.updateGenre(0, aSecondGenre), aGenre);
        assertEquals(aGenre.getGenre(), "Schmorror");
        assertEquals(aGenre.getGame().getTitle(), "Bobby Hill");
    }

    @Test
    void updateGenreNoPrev() {
        Mockito.when(rEpOsItOrY.findByGenreId(any(Integer.class))).thenReturn(null);
        Mockito.when(rEpOsItOrY.save(any(Genre.class))).then(invocationOnMock -> {
            return invocationOnMock.getArgument(0, Genre.class);
        });
        assertEquals(subject.updateGenre(0, aGenre), aGenre);
    }
}