package com.teamnathaniel.controller;

import com.teamnathaniel.model.Character;
import com.teamnathaniel.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CharacterController {
    CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @CrossOrigin
    @GetMapping("getAllCharacters")
    public List<Character> getAllCharacters() {
        return characterService.getAllCharacters();
    }

    @CrossOrigin
    @PostMapping("saveCharacter")
    public Character createCharacter(@RequestBody Character character) {
        return characterService.saveCharacter(character);
    }

    @CrossOrigin
    @GetMapping("findChar/{id}")
    public Character findCharacterById(@PathVariable int id) {
        return characterService.findCharacterById(id);
    }

    @CrossOrigin
    @GetMapping("characterName/{name}")
    public Character findCharacterByName(@PathVariable String name) {
        return characterService.findCharacterByName(name);
    }

    @CrossOrigin
    @DeleteMapping("deleteCharacter/{characterId}")
    public boolean deleteCharacter(@PathVariable int characterId) {
        characterService.deleteCharacter(characterId);
        System.out.println("Character with Id " + characterId + " was deleted.");

        return true;
    }

    @CrossOrigin
    @PutMapping("updateCharacter/{characterId}")
    public Character updateCharacter(@PathVariable int characterId, @RequestBody Character character) {
        return characterService.updateCharacter(characterId, character);
    }

}
