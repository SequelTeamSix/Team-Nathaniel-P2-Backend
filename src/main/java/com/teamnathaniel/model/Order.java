package com.teamnathaniel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "Order")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
//tentatively include json identity, maybe
public class Order {

    @Id
    private int orderId;
    @Column
    private String orderDate;
    //multiplicity relationship for User
    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

}
