package com.teamnathaniel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Game")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Game {

    @Id
    @GeneratedValue
    private int gameId;
    @Column
    private String title;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private Set<Console> console;
}