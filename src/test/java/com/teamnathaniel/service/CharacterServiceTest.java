package com.teamnathaniel.service;

import com.teamnathaniel.model.Character;
import com.teamnathaniel.repository.CharacterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


class CharacterServiceTest {

    private CharacterService subject;

    private CharacterRepository rEpOsItOrY;

    private List<Character> characters;
    private Character link;
    private static boolean deleteCalled = false;

    @BeforeEach
    void sEtUp() {
        rEpOsItOrY = Mockito.mock(CharacterRepository.class);
        subject = new CharacterService(rEpOsItOrY);

        characters = new ArrayList<>();
        Character doomGuy = new Character();
        doomGuy.setCharacterName("DoomGuy");
        doomGuy.setDescription("The guy from Doom");
        doomGuy.setImage("https://upload.wikimedia.org/wikipedia/en/f/fc/Doom_Slayer.png");
        doomGuy.setSeries(new ArrayList<>());
        doomGuy.setCatchPhrase("...");
        characters.add(doomGuy);

        link = new Character();
        link.setCharacterName("Link");
        link.setDescription("The hero of Hyrule");
        link.setImage("https://upload.wikimedia.org/wikipedia/en/2/21/Link_of_the_Wild.png");
        link.setSeries(new ArrayList<>());
        link.setCatchPhrase("Hyup!");
    }

    @Test
    void getAllCharacters() {
        when(rEpOsItOrY.findAll()).thenReturn(characters);
        assertEquals(subject.getAllCharacters(), characters);
    }

    @Test
    void saveCharacter() {
        when(rEpOsItOrY.save(any(Character.class))).thenReturn(link);
        assertEquals(subject.saveCharacter(link), link);
    }

    @Test
    void findCharacterByName() {
        when(rEpOsItOrY.findByName(any(String.class))).thenReturn(link);
        assertEquals(subject.findCharacterByName("Link"), link);
    }

    @Test
    void deleteCharacter() {
        Mockito.doAnswer(invocationOnMock -> {
            deleteCalled = true;
            return null;
        }).when(rEpOsItOrY).deleteByCharacterId(any(Integer.class));
        subject.deleteCharacter(0);
        assertTrue(deleteCalled);
    }

    @Test
    void findCharacterById() {
        when(rEpOsItOrY.findById(any(Integer.class))).thenReturn(link);
        assertEquals(subject.findCharacterById(0), link);
    }

    @Test
    void updateCharacter() {
        when(rEpOsItOrY.findById(0)).thenReturn(link);
        when(rEpOsItOrY.save(any(Character.class))).thenReturn(link);
        Character newLink = new Character();
        newLink.setCatchPhrase("Hyaaa!");
        newLink.setCharacterName("Not Zelda");
        newLink.setDescription("User of the master sword");
        newLink.setImage("FakeImage.jpg");
        assertEquals(subject.updateCharacter(0, newLink), link);
        assertEquals(link.getCatchPhrase(), "Hyaaa!");
        assertEquals(link.getCharacterName(), "Not Zelda");
        assertEquals(link.getDescription(), "User of the master sword");
        assertEquals(link.getImage(), "FakeImage.jpg");
    }

    @Test
    void updateCharacterNoPrev() {
        when(rEpOsItOrY.findById(any(Integer.class))).thenReturn(null);
        when(rEpOsItOrY.save(any(Character.class))).then(invocationOnMock -> {
            return invocationOnMock.getArgument(0, Character.class);
        });
        assertEquals(subject.updateCharacter(0, link), link);
    }
}