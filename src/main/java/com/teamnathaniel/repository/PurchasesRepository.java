package com.teamnathaniel.repository;

import com.teamnathaniel.model.Purchases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface PurchasesRepository extends JpaRepository<Purchases, Integer> {

    @Query("From Purchases")
    List<Purchases> findAll();

    @Query("From Purchases where purchasesId = :id")
    Purchases findById(int id);

    Purchases save(Purchases purchases);

    void deleteByPurchasesId(int purchaseId);
}
