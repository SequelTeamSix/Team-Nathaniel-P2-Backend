package com.teamnathaniel.model;

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
//tentatively include json identity, maybe
public class Review {

    @Id
    private int reviewId;
    @Column
    private int reviewRating;
    @Column
    private String reviewDescription;
    //multiplicity relationship for User
    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;
}
