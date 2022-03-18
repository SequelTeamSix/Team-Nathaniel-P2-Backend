package com.teamnathaniel.controller;

import com.teamnathaniel.model.Character;
import com.teamnathaniel.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CharacterController {
    CharacterService CharacterService;

    @Autowired
    public CharacterController(CharacterService CharacterService) {
        this.CharacterService = CharacterService;
    }

    @GetMapping("getAllCharacters")
    public List<Character> getAllCharacters() {
        return CharacterService.getAllCharacters();
    }

    @PostMapping("saveCharacter")
    public Character createCharacter(@RequestBody Character Character) {
        return CharacterService.saveCharacter(Character);
    }

    @GetMapping("findChar/{id}")
    public Character findCharacterById(@PathVariable int id) {
        return CharacterService.findCharacterById(id);
    }

    @GetMapping("CharacterName/{name}")
    public Character findCharacterByName(@PathVariable String name) {
        return CharacterService.findCharacterByName(name);
    }

    @DeleteMapping("deleteCharacter/{Character}")
    public boolean deleteCharacter(@PathVariable Character Character) {
        CharacterService.deleteCharacter(Character);
        System.out.println(Character.toString() + " was deleted.");
        return true;
    }

    @PutMapping("updateCharacter")
    public void updateCharacter(@RequestBody Character Character) {
        CharacterService.updateCharacter(Character);
    }

}
