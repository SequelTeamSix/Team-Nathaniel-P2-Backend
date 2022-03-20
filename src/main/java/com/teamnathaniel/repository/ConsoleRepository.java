package com.teamnathaniel.repository;

import com.teamnathaniel.model.Character;
import com.teamnathaniel.model.Console;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ConsoleRepository extends JpaRepository<Console, Integer> {

    @Query("From Console")
    List<Console> findAll();

    @Query("from Console where consoleName = :name")
    Console findByName(String name);

    @Query("from Console where consoleId = :id")
    Console findById(int id);

    Console save(Console console);

    void deleteByConsoleId(int consoleId);
}
