package com.teamnathaniel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "Console")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
//tentatively include json identity, maybe
public class Console {

    @Id
    private int consoleId;
    @Column
    private String name;
    @Column
    private String releaseDate;
    /*
    multiplicity relationship for Game
    @ManyToMany(fetch =  FetchType.EAGER)
    @JoinColumn(name = "gameId")
    private Game game;
    */

}
