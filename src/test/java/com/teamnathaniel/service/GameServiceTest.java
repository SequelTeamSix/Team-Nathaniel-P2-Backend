package com.teamnathaniel.service;

import com.teamnathaniel.model.Game;
import com.teamnathaniel.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class GameServiceTest {
    private GameService subject;

    private GameRepository rEpOsItOrY;

    private List<Game> allGames;
    private Game fallout;

    private static boolean deleteCalled = false;

    @BeforeEach
    void setUp() {
        rEpOsItOrY = Mockito.mock(GameRepository.class);
        subject = new GameService(rEpOsItOrY);

        allGames = new ArrayList<>();
        Game fuzionFrenzy = new Game();
        fuzionFrenzy.setReleaseDate("idk");
        fuzionFrenzy.setOnline(false);
        fuzionFrenzy.setNumPlayers(4);
        fuzionFrenzy.setGamePrice(3);
        fuzionFrenzy.setTitle("Fuzion Frenzy");
        fuzionFrenzy.setBoxArt("FakeImage.jpg");
        allGames.add(fuzionFrenzy);

        fallout = new Game();
        fallout.setBoxArt("AlsoFakeImage.jpg");
        fallout.setReleaseDate("too tired to look it up");
        fallout.setGamePrice(30);
        fallout.setOnline(false);
        fallout.setNumPlayers(1);
        fallout.setTitle("Fallout");
    }

    @Test
    void getAllGames() {
        Mockito.when(rEpOsItOrY.findAll()).thenReturn(allGames);
        assertEquals(subject.getAllGames(), allGames);
    }

    @Test
    void saveGame() {
        Mockito.when(rEpOsItOrY.save(any(Game.class))).thenReturn(fallout);
        assertEquals(subject.saveGame(fallout), fallout);
    }

    @Test
    void findGameByName() {
        Mockito.when(rEpOsItOrY.findByName(any(String.class))).thenReturn(fallout);
        assertEquals(subject.findGameByName("Fallout"), fallout);
    }

    @Test
    void findGameById() {
        Mockito.when(rEpOsItOrY.findById(0)).thenReturn(fallout); // Why doesn't this work with any(Integer.class) you ask? No idea but it has something to do with Optional return values.
        assertEquals(subject.findGameById(0), fallout);
    }

    @Test
    void deleteGame() {
        Mockito.doAnswer(invocationOnMock -> {
            deleteCalled = true;
            return null;
        }).when(rEpOsItOrY).deleteByGameId(any(Integer.class));
        subject.deleteGame(0);
        assertTrue(deleteCalled);
    }

    @Test
    void updateGame() {
        Mockito.when(rEpOsItOrY.findById(0)).thenReturn(fallout);
        Mockito.when(rEpOsItOrY.save(any(Game.class))).thenReturn(fallout);
        Game newFallout = new Game();
        newFallout.setTitle("Filloot");
        newFallout.setGamePrice(125);
        newFallout.setOnline(true);
        newFallout.setReleaseDate("12,500 B.C.");
        newFallout.setBoxArt("Flout.jpg");
        newFallout.setNumPlayers(3);
        assertEquals(subject.updateGame(0, newFallout), fallout);
        assertEquals(fallout.getTitle(), "Filloot");
        assertEquals(fallout.getGamePrice(), 125);
        assertEquals(fallout.isOnline(), true);
        assertEquals(fallout.getReleaseDate(), "12,500 B.C.");
        assertEquals(fallout.getBoxArt(), "Flout.jpg");
        assertEquals(fallout.getNumPlayers(), 3);
    }

    @Test
    void updateGameNoPrev() {
        Mockito.when(rEpOsItOrY.findById(any(Integer.class))).thenReturn(null);
        Mockito.when(rEpOsItOrY.save(any(Game.class))).then(invocationOnMock -> {
            return invocationOnMock.getArgument(0, Game.class);
        });
        assertEquals(subject.updateGame(0, fallout), fallout);
    }
}
