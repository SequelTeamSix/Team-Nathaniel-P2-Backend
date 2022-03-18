package com.teamnathaniel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany(fetch =  FetchType.EAGER)
    @JoinColumn(name = "gameId")
    private List<Game> game;


}
