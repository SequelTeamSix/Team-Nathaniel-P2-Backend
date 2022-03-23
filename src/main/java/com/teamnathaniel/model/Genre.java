package com.teamnathaniel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "Genre")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class Genre {

    @Id
    private int genreId;
    @Column
    private String genre;
    //multiplicity relationship for Game
    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "gameId")
    private Game game;


}