package com.teamnathaniel.controller;

import com.teamnathaniel.model.Console;
import com.teamnathaniel.service.ConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConsoleController {
    ConsoleService consoleService;

    @Autowired
    public ConsoleController(ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    @GetMapping("getAllConsoles")
    public List<Console> getAllConsoles() {
        return consoleService.getAllConsoles();
    }

    @PostMapping("saveConsole")
    public Console createConsole(@RequestBody Console console) {
        return consoleService.saveConsole(console);
    }

    @GetMapping("findConsole/{id}")
    public Console findConsoleById(@PathVariable int id) {
        return consoleService.findConsoleById(id);
    }

    @GetMapping("consoleName/{name}")
    public Console findConsoleByName(@PathVariable String name) {
        return consoleService.findConsoleByName(name);
    }
    @DeleteMapping("deleteConsole/{consoleId}")
    public boolean deleteConsole(@PathVariable int consoleId) {
        consoleService.deleteConsole(consoleId);
        System.out.println("Console with Id " + consoleId + " was deleted.");
        return true;
    }

    @PutMapping("updateConsole/{consoleId}")
    public Console updateConsole(@PathVariable int consoleId, @RequestBody Console console) {
        return consoleService.updateConsole(consoleId, console);
    }
}
