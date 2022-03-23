package com.teamnathaniel.controller;

import com.teamnathaniel.model.GameOrder;
import com.teamnathaniel.service.GameOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameOrderController {
    GameOrderService gameOrderService;

    @Autowired
    public GameOrderController(GameOrderService gameOrderService) {
        this.gameOrderService = gameOrderService;
    }

    @CrossOrigin
    @GetMapping("getAllGameOrders")
    public List<GameOrder> getAllGameOrders(){
        return gameOrderService.getAllGameOrders();
    }

    @CrossOrigin
    @PostMapping("saveGameOrder")
    public GameOrder createGameOrder(@RequestBody GameOrder gameOrder){
        return gameOrderService.saveGameOrder(gameOrder);
    }

    @CrossOrigin
    @GetMapping("findGameOrder/{gameOrderId}")
    public GameOrder findGameOrderById(@PathVariable int gameOrderId){
        return gameOrderService.findGameOrderById(gameOrderId);
    }

    @CrossOrigin
    @DeleteMapping("deleteGameOrder/{gameOrderId}")
    public boolean deleteGameOrder(@PathVariable int gameOrderId){
        gameOrderService.deleteGameOrder(gameOrderId);
        System.out.println("GameOrder with id " + gameOrderId + " was deleted. ");
        return true;
    }

    @CrossOrigin
    @PutMapping("updateGameOrder/{gameOrderId}")
    public GameOrder updateGameOrder(@PathVariable int gameOrderId, @RequestBody GameOrder gameOrder){
        return gameOrderService.updateGameOrder(gameOrderId, gameOrder);
    }
}
