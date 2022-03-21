package com.teamnathaniel.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "purchasesId")
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
