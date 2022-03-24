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
             if(gameOrder.getQuantity() != 0){ // I was debating this conditional, but it makes sense. if you have 0, then it's not really a gameOrder and you should just delete it instead.
                 oldGameOrder.setQuantity(gameOrder.getQuantity());
             }
             if (gameOrder.getGame() != null) {
                 oldGameOrder.setGame(gameOrder.getGame());
             }
             if (gameOrder.getPurchases() != null) {
                 oldGameOrder.setPurchases(gameOrder.getPurchases());
             }
             return gameOrderRepository.save(oldGameOrder);
         } else {
             return gameOrderRepository.save(gameOrder);
         }
    }
}
