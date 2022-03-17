package com.teamnathaniel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "Character")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
//tentatively include json identity, maybe
public class Character {

    @Id
    private int characterId;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String image;
    //inquire tomorrow about this
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "seriesId")
    private Series series;
}
