package com.teamnathaniel.service;

import com.teamnathaniel.model.Purchases;
import com.teamnathaniel.repository.PurchasesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PurchasesService {
    PurchasesRepository purchasesRepository;

    @Autowired
    public PurchasesService(PurchasesRepository purchasesRepository) {
        this.purchasesRepository = purchasesRepository;
    }

    public List<Purchases> getAllPurchases(){
        return purchasesRepository.findAll();
    }

    public Purchases savePurchase(Purchases purchases){
        return purchasesRepository.save(purchases);
    }

    public Purchases findPurchaseById(int purchaseId){
        return purchasesRepository.findById(purchaseId);
    }

    public void deletePurchase(int purchaseId){
        purchasesRepository.deleteByPurchasesId(purchaseId);
    }

    public Purchases updatePurchase(int purchaseId, Purchases purchases){
        Purchases oldPurchase = purchasesRepository.findById(purchaseId);
        if(oldPurchase != null){
            if(purchases.getOrderDate() != null){
                oldPurchase.setOrderDate(purchases.getOrderDate());
            }
            if (purchases.getCustomer() != null) {
                oldPurchase.setCustomer(purchases.getCustomer());
            }
            if (purchases.getGameOrders() != null) {
                oldPurchase.setGameOrders(purchases.getGameOrders());
            }
            return purchasesRepository.save(oldPurchase);
        }
        return purchasesRepository.save(purchases);
    }
}
