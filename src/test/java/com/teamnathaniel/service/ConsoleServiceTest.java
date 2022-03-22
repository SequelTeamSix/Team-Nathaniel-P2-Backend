package com.teamnathaniel.service;

import com.teamnathaniel.model.Console;
import com.teamnathaniel.model.ConsoleFeature;
import com.teamnathaniel.model.Game;
import com.teamnathaniel.repository.ConsoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class ConsoleServiceTest {

    private ConsoleService subject;

    private ConsoleRepository rEpOsItOrY;

    private List<Console> allConsoles;

    private Console xbox;

    private static boolean deleteCalled = false;
    @BeforeEach
    void setUp() {
        rEpOsItOrY = Mockito.mock(ConsoleRepository.class);
        subject = new ConsoleService(rEpOsItOrY);

        allConsoles = new ArrayList<>();
        xbox = new Console();
        xbox.setConsoleName("Xbox");
        xbox.setReleaseDate("The year 3001");
        xbox.setLogo("Logo.jpg");
        xbox.setPicture("Picture.jpg");
        xbox.setConsoleFeature(new ArrayList<>());
        xbox.setGame(new ArrayList<>());
        allConsoles.add(xbox);
    }

    @Test
    void getAllConsoles() {
        Mockito.when(rEpOsItOrY.findAll()).thenReturn(allConsoles);
        assertEquals(subject.getAllConsoles(), allConsoles);
    }

    @Test
    void saveConsole() {
        Mockito.when(rEpOsItOrY.save(xbox)).thenReturn(xbox);
        assertEquals(subject.saveConsole(xbox), xbox);
    }

    @Test
    void findConsoleByName() {
        Mockito.when(rEpOsItOrY.findByName(any(String.class))).thenReturn(xbox);
        assertEquals(subject.findConsoleByName("Xbox"), xbox);
    }

    @Test
    void deleteConsole() {
        Mockito.doAnswer(invocationOnMock -> {
            deleteCalled = true;
            return null;
        }).when(rEpOsItOrY).deleteByConsoleId(any(Integer.class));
        subject.deleteConsole(0);
        assertTrue(deleteCalled);
    }

    @Test
    void findConsoleById() {
        Mockito.when(rEpOsItOrY.findById(0)).thenReturn(xbox);
        assertEquals(subject.findConsoleById(0), xbox);
    }

    @Test
    void updateConsole() {
        Mockito.when(rEpOsItOrY.findById(0)).thenReturn(xbox);
        Mockito.when(rEpOsItOrY.save(any(Console.class))).then(invocationOnMock -> {
            return invocationOnMock.getArgument(0, Console.class);
        });
        Console nextbox = new Console();
        nextbox.setConsoleId(5);
        nextbox.setConsoleName("Xboox");
        nextbox.setReleaseDate("A distant time, far removed from the bounds of humanity");
        nextbox.setLogo("Shlogo.jpg");
        nextbox.setPicture("Shpicture.jpg");
        List<Game> newGameList = new ArrayList<>();
        newGameList.add(new Game());
        nextbox.setGame(newGameList);
        List<ConsoleFeature> newFeatureList = new ArrayList<>();
        newFeatureList.add(new ConsoleFeature());
        nextbox.setConsoleFeature(newFeatureList);
        assertEquals(subject.updateConsole(0, nextbox), xbox);
        assertEquals(xbox.getConsoleName(), "Xboox");
        assertEquals(xbox.getReleaseDate(), "A distant time, far removed from the bounds of humanity");
        assertEquals(xbox.getLogo(), "Shlogo.jpg");
        assertEquals(xbox.getPicture(), "Shpicture.jpg");
        assertEquals(xbox.getGame(), newGameList);
        assertEquals(xbox.getConsoleFeature(), newFeatureList);
    }

    @Test
    void updateConsoleNoPrev() {
        Mockito.when(rEpOsItOrY.findById(0)).thenReturn(null);
        Mockito.when(rEpOsItOrY.save(any(Console.class))).then(invocationOnMock -> {
            return invocationOnMock.getArgument(0, Console.class);
        });
        assertEquals(subject.updateConsole(0, xbox), xbox);
    }
}