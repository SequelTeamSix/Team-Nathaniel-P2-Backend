package com.teamnathaniel.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "Review")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "reviewId")
public class Review {

    @Id
    private int reviewId;
    @Column
    private int reviewRating;
    @Column
    private String reviewDescription;
    //multiplicity relationship for User
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private Customer customer;
}
