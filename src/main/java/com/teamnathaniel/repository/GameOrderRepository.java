package com.teamnathaniel.repository;

import com.teamnathaniel.model.GameOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

import java.util.List;

@Transactional
public interface GameOrderRepository extends JpaRepository<GameOrder, Integer> {

    @Query("From GameOrder")
    List<GameOrder> findAll();

    @Query("from GameOrder where gameOrderId = :gameOrderId")
    GameOrder findByGameOrderId(int gameOrderId);

    GameOrder save(GameOrder gameOrder);

    void deleteByGameOrderId(int gameOrderId);
}
