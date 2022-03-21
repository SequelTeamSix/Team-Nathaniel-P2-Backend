package com.teamnathaniel.controller;

import com.teamnathaniel.model.Purchases;
import com.teamnathaniel.service.PurchasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PurchasesController {
    PurchasesService purchasesService;

    @Autowired
    public PurchasesController(PurchasesService purchasesService) {
        this.purchasesService = purchasesService;
    }

    @GetMapping("getAllPurchases")
    public List<Purchases> getAllPurchase(){
        return purchasesService.getAllPurchases();
    }

    @PostMapping("savePurchase")
    public Purchases createPurchase(@RequestBody Purchases purchases){
        return purchasesService.savePurchase(purchases);
    }

    @GetMapping("findPurchase/{id}")
    public Purchases findPurchaseById(@PathVariable int id){
        return purchasesService.findPurchaseById(id);
    }

    @DeleteMapping("deletePurchase/{purchaseId}")
    public boolean deletePurchase(@PathVariable int purchaseId){
        purchasesService.deletePurchase(purchaseId);
        System.out.println("Purchase with id " +purchaseId + " was deleted.");
        return true;
    }

    @PutMapping("updatePurchase/{purchaseId}")
    public Purchases updatePurchase(@PathVariable int purchaseId, @RequestBody Purchases purchases){
        return purchasesService.updatePurchase(purchaseId,purchases);
    }
}
