package com.teamnathaniel.controller;

import com.teamnathaniel.model.Game;
import com.teamnathaniel.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {
    GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @CrossOrigin
    @GetMapping("getAllGames")
    public List<Game> getAllGames(){
        return gameService.getAllGames();
    }

    @CrossOrigin
    @PostMapping("saveGame")
    public Game createGame(@RequestBody Game game){
        return gameService.saveGame(game);
    }

    @CrossOrigin
    @GetMapping("findGame/{id}")
    public Game findGameById(@PathVariable int id){
        return gameService.findGameById(id);
    }

    @CrossOrigin
    @GetMapping("gameName/{name}")
    public Game findGameByName(@PathVariable String name){
        return gameService.findGameByName(name);
    }

    @CrossOrigin
    @DeleteMapping("deleteGame/{gameId}")
    public boolean deleteGame(@PathVariable int gameId){
        gameService.deleteGame(gameId);
        System.out.println("Game with Id " + gameId + " was deleted.");
        return true;
    }

    @CrossOrigin
    @PutMapping("updateGame/{gameId}")
    public Game updateGame(@PathVariable int gameId, @RequestBody Game game){
        return gameService.saveGame(game);
    }

}
