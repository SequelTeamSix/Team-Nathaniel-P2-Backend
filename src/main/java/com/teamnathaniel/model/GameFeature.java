package com.teamnathaniel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "GameFeature")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class GameFeature {

    @Id
    private int gameFeatureId;
    @Column
    private String title;
    @Column
    private String body;
    @Column
    private boolean isMainFeature;
    //multiplicity relationship for Game
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "gameId")
    private Game game;


}
