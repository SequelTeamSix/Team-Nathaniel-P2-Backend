package com.teamnathaniel.service;

import com.teamnathaniel.model.Character;
import com.teamnathaniel.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CharacterService {
    CharacterRepository characterRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public List<Character> getAllCharacters(){
        return characterRepository.findAll();
    }

    public Character saveCharacter(Character character){
        return characterRepository.save(character);
    }

    public Character findCharacterByName(String name){
        return characterRepository.findByName(name);
    }

    public void deleteCharacter(Character character){
        characterRepository.delete(character);
    }

    public Character findCharacterById(int id){
        return characterRepository.findById(id);
    }

    public void updateCharacter(Character character){
        //if character is in DB, update the requested info based on provided
        if(characterRepository.findAll().contains(character))
        {
            character.setCharacterId(character.getCharacterId());
            character.setCharacterName(character.getCharacterName());
            character.setDescription(character.getDescription());
            character.setImage(character.getImage());
            character.setCatchPhrase(character.getCatchPhrase());
        }
        //character isn't in DB, go ahead and create one with provided info.
        else {
            saveCharacter(character);
        }
    }
}
