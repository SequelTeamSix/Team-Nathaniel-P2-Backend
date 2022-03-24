package com.teamnathaniel.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Character")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Character {

    @Id
    private int characterId;
    @Column
    private String characterName;
    @Column
    private String description;
    @Column
    private String image;
    @Column
    private String catchPhrase;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "seriesId")
    @JsonIgnoreProperties("character")
    private List<Series> series;
}