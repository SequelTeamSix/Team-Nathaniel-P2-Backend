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

    public Game updateGame(int gameId, Game game){
        Game oldGame = gameRepository.findById(gameId);
        //if game is in DB, update the requested info based on provided
        if (oldGame != null) {
            oldGame.setGamePrice(game.getGamePrice());
            oldGame.setOnline(game.isOnline());
            oldGame.setNumPlayers(game.getNumPlayers());
            if (game.getTitle() != null) {
                oldGame.setTitle(game.getTitle());
            }
            if (game.getBoxArt() != null) {
                oldGame.setBoxArt(game.getBoxArt());
            }
            if (game.getReleaseDate() != null) {
                oldGame.setReleaseDate(game.getReleaseDate());
            }
            return saveGame(oldGame);
        }
        //game isn't in DB, go ahead and create one with provided info.
        else {
            return saveGame(game);
        }
    }
}
