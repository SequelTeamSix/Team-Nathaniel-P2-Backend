package com.teamnathaniel.service;

import com.teamnathaniel.model.Character;
import com.teamnathaniel.model.Game;
import com.teamnathaniel.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameService {
    GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }
    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }

    public Game saveGame(Game game){
        return gameRepository.save(game);
    }

    public Game findGameByName(String name){
        return gameRepository.findByName(name);
    }

    public Game findGameById(int id){
        return gameRepository.findById(id);
    }

    public void deleteGame(int gameId){
        gameRepository.deleteByGameId(gameId);
    }

    public void updateGame(Game game){
        //if game is in DB, update the requested info based on provided
        if(gameRepository.findAll().contains(game))
        {
            game.setGameId(game.getGameId());
            game.setTitle(game.getTitle());
            game.setReleaseDate(game.getReleaseDate());
            game.setGamePrice(game.getGamePrice());
            game.setNumPlayers(game.getNumPlayers());
            game.setOnline(game.isOnline());
            game.setBoxArt(game.getBoxArt());

        }
        //game isn't in DB, go ahead and create one with provided info.
        else {
            saveGame(game);
        }
    }
}
