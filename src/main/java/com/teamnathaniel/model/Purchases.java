package com.teamnathaniel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "Purchases")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
//tentatively include json identity, maybe
public class Purchases {

    @Id
    private int purchasesId;
    @Column
    private String orderDate;
    //multiplicity relationship for User
    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "customerId")
    private Customer customer;

}
