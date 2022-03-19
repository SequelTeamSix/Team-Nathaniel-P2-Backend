package com.teamnathaniel.service;

import com.teamnathaniel.model.Console;
import com.teamnathaniel.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsoleService {
    ConsoleRepository consoleRepository;

    @Autowired
    public ConsoleService(ConsoleRepository consoleRepository) {
        this.consoleRepository = consoleRepository;
    }

    public List<Console> getAllConsoles(){
        return consoleRepository.findAll();
    }

    public Console saveConsole(Console console){
        return consoleRepository.save(console);
    }

    public Console findConsoleByName(String name){
        return consoleRepository.findByName(name);
    }

    public void deleteConsole(int characterId){
        consoleRepository.deleteByConsoleId(characterId);
    }

    public Console findConsoleById(int id){
        return consoleRepository.findById(id);
    }

    public Console updateConsole(int characterId, Console console){
        Console oldConsole = consoleRepository.findById(characterId);
        //if console is in DB, update the requested info based on provided
        if (oldConsole != null) {
            console.setConsoleId(console.getConsoleId());
            if (console.getConsoleName() != null) {
                oldConsole.setConsoleName(console.getConsoleName());
            }
            if (console.getReleaseDate() != null) {
                oldConsole.setReleaseDate(oldConsole.getReleaseDate());
            }
        }
        // console isn't in DB, go ahead and create one with provided info.
        return saveConsole(console);
    }
}
