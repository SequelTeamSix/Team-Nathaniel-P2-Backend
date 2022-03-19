package com.teamnathaniel.controller;

import com.teamnathaniel.model.Character;
import com.teamnathaniel.service.CharacterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(CharacterController.class)
class CharacterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CharacterService sErViCe;

    private List<Character> characters;
    private Character link;

    @BeforeEach
    public void sEtUp() {
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
    public void getAllCharacters() throws Exception{
        when(sErViCe.getAllCharacters()).thenReturn(characters);
        this.mockMvc.perform(get("/getAllCharacters"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"characterId\" : 0, \"characterName\" : \"DoomGuy\", \"description\" : \"The guy from Doom\", \"image\" : \"https://upload.wikimedia.org/wikipedia/en/f/fc/Doom_Slayer.png\", \"catchPhrase\" : \"...\", \"series\" : [] }]"));
    }

    @Test
    void createCharacter() throws Exception{
        when(sErViCe.saveCharacter(any(Character.class))).thenReturn(link);
        this.mockMvc.perform(post("/saveCharacter")
                        .content("{\"characterName\":\"Link\",\"description\":\"The hero of Hyrule\", \"image\":\"https://upload.wikimedia.org/wikipedia/en/2/21/Link_of_the_Wild.png\", \"catchPhrase\" : \"Hyup!\", \"series\": [] }")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"characterName\":\"Link\",\"description\":\"The hero of Hyrule\", \"image\":\"https://upload.wikimedia.org/wikipedia/en/2/21/Link_of_the_Wild.png\", \"catchPhrase\" : \"Hyup!\", \"series\": [] }"));
    }

    @Test
    void findCharacterById() throws Exception{
        when(sErViCe.findCharacterById(any(Integer.class))).thenReturn(link);
        this.mockMvc.perform(get("/findChar/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"characterName\":\"Link\",\"description\":\"The hero of Hyrule\", \"image\":\"https://upload.wikimedia.org/wikipedia/en/2/21/Link_of_the_Wild.png\", \"catchPhrase\" : \"Hyup!\", \"series\": [] }"));
    }

    @Test
    void findCharacterByName() throws Exception {
        when(sErViCe.findCharacterByName(any(String.class))).thenReturn(link);
        this.mockMvc.perform(get("/CharacterName/Link"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"characterName\":\"Link\",\"description\":\"The hero of Hyrule\", \"image\":\"https://upload.wikimedia.org/wikipedia/en/2/21/Link_of_the_Wild.png\", \"catchPhrase\" : \"Hyup!\", \"series\": []}"));
    }

    @Test
    void deleteCharacter() throws Exception {
        Mockito.doNothing().when(sErViCe).deleteCharacter(any(Integer.class));
        this.mockMvc.perform(delete("/deleteCharacter/0"))
                .andDo(print())
                .andExpect(status().isOk()); // the json parser doesn't want to parse the literal value true, so I can't test the return value of this endpoint
    }

    @Test
    void updateCharacter() throws Exception {
        when(sErViCe.updateCharacter(any(Integer.class), any(Character.class))).thenReturn(link);
        this.mockMvc.perform(put("/updateCharacter/0")
                        .content("{\"characterName\":\"Link\",\"description\":\"The hero of Hyrule\", \"image\":\"https://upload.wikimedia.org/wikipedia/en/2/21/Link_of_the_Wild.png\", \"catchPhrase\" : \"Hyup!\", \"series\": [] }")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"characterName\":\"Link\",\"description\":\"The hero of Hyrule\", \"image\":\"https://upload.wikimedia.org/wikipedia/en/2/21/Link_of_the_Wild.png\", \"catchPhrase\" : \"Hyup!\", \"series\": [] }"));
    }
}