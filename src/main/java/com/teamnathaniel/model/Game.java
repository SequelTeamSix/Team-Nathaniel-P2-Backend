package com.teamnathaniel.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


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
    @JsonIgnoreProperties({"character", "game"})
    private Series series;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable
    @JsonIgnoreProperties({"game","consoleFeature"})
    private List<Console> console;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "gameFeatureId")
    @JsonIgnoreProperties("game")
    private List<GameFeature> gameFeatures;
}