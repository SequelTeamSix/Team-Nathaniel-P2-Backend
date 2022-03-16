package com.teamnathaniel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "Game")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
//tentatively include json identity, maybe
public class Game {

    @Id
    private int gameId;
    @Column
    private int title;
    @Column
    private String releaseDate;
    @Column
    private int gamePrice;
    @Column
    private int numPlayers;
    @Column
    private boolean isOnline;
    @Column
    private String boxArt;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "seriesId")
    private Series series;
    //inquire about Console M2M relationship
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "consoleId")
    private Console console;

}