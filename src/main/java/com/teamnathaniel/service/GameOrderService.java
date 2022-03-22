package com.teamnathaniel.service;

import com.teamnathaniel.model.GameOrder;
import com.teamnathaniel.repository.GameOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameOrderService {
    GameOrderRepository gameOrderRepository;

    @Autowired
    public GameOrderService(GameOrderRepository gameOrderRepository) {
        this.gameOrderRepository = gameOrderRepository;
    }

    public List<GameOrder> getAllGameOrders(){
        return gameOrderRepository.findAll();
    }

    public GameOrder saveGameOrder(GameOrder gameOrder){
        return gameOrderRepository.save(gameOrder);
    }

    public GameOrder findGameOrderById(int gameOrderId){
        return gameOrderRepository.findByGameOrderId(gameOrderId);
    }

    public void deleteGameOrder(int gameOrderId){
        gameOrderRepository.deleteByGameOrderId(gameOrderId);
    }

    public GameOrder updateGameOrder(int gameOrderId, GameOrder gameOrder){
         GameOrder oldGameOrder = gameOrderRepository.findByGameOrderId(gameOrderId);
         if(oldGameOrder != null){
             gameOrder.setGameOrderId(gameOrder.getGameOrderId());

             if(gameOrder.getQuantity() != 0){
                 gameOrder.setQuantity(gameOrder.getGameOrderId());
             }
         }
         return gameOrderRepository.save(gameOrder);
    }
}
