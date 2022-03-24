package com.teamnathaniel.service;

import com.teamnathaniel.model.Character;
import com.teamnathaniel.repository.CharacterRepository;
import com.teamnathaniel.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CharacterService {
    CharacterRepository characterRepository;

    @Autowired
    SeriesRepository seriesRepository;

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

    public void deleteCharacter(int characterId){
        characterRepository.deleteByCharacterId(characterId);
    }

    public Character findCharacterById(int id){
        return characterRepository.findById(id);
    }

    public Character updateCharacter(int characterId, Character character){
        Character oldChar = characterRepository.findById(characterId);
        //if character is in DB, update the requested info based on provided
        if (oldChar != null) {
            character.setCharacterId(oldChar.getCharacterId());
            if (character.getCharacterName() != null) {
                oldChar.setCharacterName(character.getCharacterName());
            }
            if (character.getDescription() != null) {
                oldChar.setDescription(character.getDescription());
            }
            if (character.getCatchPhrase() != null) {
                oldChar.setCatchPhrase(character.getCatchPhrase());
            }
            if (character.getImage() != null) {
                oldChar.setImage(character.getImage());
            }
            return saveCharacter(oldChar);
        }
        // character isn't in DB, go ahead and create one with provided info.
        else {
            return saveCharacter(character);
        }
    }
}
