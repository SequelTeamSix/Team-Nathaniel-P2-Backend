package com.teamnathaniel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "GameOrder")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class GameOrder {

    @Id
    private int gameOrderId;
    @Column
    private int quantity;
    //multiplicity relationships for Game and Purchases
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "gameId")
    private Game game;
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "purchasesId")
    private Purchases purchases;

}