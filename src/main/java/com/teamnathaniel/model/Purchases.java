package com.teamnathaniel.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Purchases")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "purchasesId")
public class Purchases {

    @Id
    @GeneratedValue
    private int purchasesId;
    @Column
    private String orderDate;
    //multiplicity relationship for User
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "gameOrderId")
    private List<GameOrder> gameOrders;

}
