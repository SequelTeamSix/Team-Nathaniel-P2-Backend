package com.teamnathaniel.repository;

import com.teamnathaniel.model.Game;
import com.teamnathaniel.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface GameRepository extends JpaRepository<Game, Integer> {

    @Query("From Game")
    List<Game> findAll();

    @Query("from Game where GameName = :name")
    Game findByName(String name);

    @Query("from Game where GameId = :id")
    Game findById(int id);

    Game save(Game Game);

    void deleteByGameId(int gameId);
}
